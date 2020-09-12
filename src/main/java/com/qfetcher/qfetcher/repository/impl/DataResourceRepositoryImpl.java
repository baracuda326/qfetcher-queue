package com.qfetcher.qfetcher.repository.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.qfetcher.qfetcher.exception.DataResourceException;
import com.qfetcher.qfetcher.exception.HttpConnectionException;
import com.qfetcher.qfetcher.model.QuestionCsvModel;
import com.qfetcher.qfetcher.repository.DataResourceRepository;
import static com.qfetcher.qfetcher.utils.FilesClearUtils.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.qfetcher.qfetcher.utils.Constants.FIELD_DESCRIPTION;

/**
 * @author Dmitry Asmalouski
 * @version 2.0
 */
@Repository
public class DataResourceRepositoryImpl implements DataResourceRepository {
    @Value("${target.url}")
    private String visionApiUrl;
    @Value("${api.key}")
    private String visionApiKey;
    private ObjectMapper objectMapper;

    @Autowired
    public DataResourceRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    //****************************************************************************

    /**
     * Detects text in the specified base64 encoded image
     * read line by line and parses json response to JsonNode
     *
     * @param imageBase base64 encoded image
     * @return JsonNode
     * @throws IOException
     * @throws HttpConnectionException
     */
    @Override
    public JsonNode getTextFromImageSource(String imageBase) throws IOException {
        URL serverUrl = new URL(visionApiUrl + visionApiKey);
        URLConnection urlConnection = serverUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setDoOutput(true);
        BufferedWriter httpRequestBodyWriter = new BufferedWriter(new
                OutputStreamWriter(httpConnection.getOutputStream()));
        String postRequest = "{ \"requests\": [{ \"image\":{ \"content\": " + "\" " + imageBase + "\" "
                + "},\"features\": [{ \"type\":\"TEXT_DETECTION\",\"maxResults\": 1 } ]}]}";
        httpRequestBodyWriter.write(postRequest);
        httpRequestBodyWriter.close();
        if (httpConnection.getInputStream() == null) {
            throw new HttpConnectionException("Connection conflict!");
        }
        Scanner httpResponseScanner = new Scanner(httpConnection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (httpResponseScanner.hasNext()) {
            response.append(httpResponseScanner.nextLine());
        }
        httpResponseScanner.close();
        return objectMapper.readTree(response.toString()).findValue(FIELD_DESCRIPTION);
    }

    //****************************************************************************

    /**
     * Read line by line and parses csv file to List<JsonNode>
     *
     * @param fileName resource name
     * @return List<JsonNode>
     */
    @SneakyThrows
    @Override
    public List<JsonNode> parseCSVFile(String fileName) {
        List<JsonNode> csvList;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            csvList = new ArrayList<>();
            String[] record;
            //skip header row
            reader.readNext();
            JsonNode node;
            QuestionCsvModel csv;
            while ((record = reader.readNext()) != null) {
                csv = QuestionCsvModel.builder()
                        .id(Long.parseLong(record[0]))
                        .text(record[1])
                        .field(record[2])
                        .build();
                node = objectMapper.valueToTree(csv);
                csvList.add(node);
            }
        } catch (CsvValidationException e) {
            throw new DataResourceException("No valid data resource!");
        }
        removeDirFile(fileName);
        return csvList;
    }

    //****************************************************************************

    /**
     * Parses json to JsonNode
     *
     * @param fileName
     * @return JsonNode
     * @throws DataResourceException
     */
    @Override
    public JsonNode parseJsonFile(String fileName) throws IOException {
        JsonNode questions;
        questions = objectMapper.readTree(Files.newBufferedReader(Paths.get(fileName)));
        removeDirFile(fileName);
        return questions;
    }
//****************************************************************************
}