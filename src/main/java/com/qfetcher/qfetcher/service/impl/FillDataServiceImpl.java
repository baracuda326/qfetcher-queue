package com.qfetcher.qfetcher.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.qfetcher.qfetcher.manager.broker.Broker;
import com.qfetcher.qfetcher.model.QuestionResponseModel;
import com.qfetcher.qfetcher.repository.DataResourceRepository;
import com.qfetcher.qfetcher.service.FillDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.qfetcher.qfetcher.utils.Constants.*;
import static com.qfetcher.qfetcher.utils.FilesClearUtils.removeDirFile;
import static com.qfetcher.qfetcher.utils.ParseDataUtils.*;

@Service
public class FillDataServiceImpl implements FillDataService {
    private final DataResourceRepository dataResourceRepository;
    private final Broker broker;

    @Autowired
    public FillDataServiceImpl(DataResourceRepository dataResourceRepository, Broker broker) {
        this.dataResourceRepository = dataResourceRepository;
        this.broker = broker;
    }

    @Override
    public void fillData(Map<String, HashSet<String>> map) throws IOException {
        for (String key : map.keySet()) {
            if (key.equals(IMAGE_TYPE)) {
                fillImages(map.getOrDefault(IMAGE_TYPE, new HashSet<>()));
            }
            if (key.equals(JSON_TYPE)) {
                fillJson(map.getOrDefault(JSON_TYPE, new HashSet<>()));
            }
            if (key.equals(CSV_TYPE)) {
                fillCsv(map.getOrDefault(CSV_TYPE, new HashSet<>()));
            }
        }
    }
    //***************************************************************************

    private void fillJson(HashSet<String> links) throws IOException {
        if (links != null) {
            for (String link : links) {
                String jsonName = getFileName(link);
                createFileFromUrl(link, jsonName);
                JsonNode jsonNode = dataResourceRepository.parseJsonFile(jsonName);
                List<JsonNode> jsonNodeList = jsonNode.findValues(KEY_TEXT);
                for (JsonNode jsonNodeBuf : jsonNodeList) {
                    broker.put(QuestionResponseModel.builder()
                            .value(jsonNodeBuf.textValue())
                            .source(JSON_TYPE)
                            .build());
                }
            }
        }
    }

    //***************************************************************************

    private void fillCsv(HashSet<String> links) {
        for (String link : links) {
            String csvName = getFileName(link);
            createFileFromUrl(link, csvName);
            List<JsonNode> jsonNodeList = dataResourceRepository.parseCSVFile(csvName);
            for (JsonNode jsonNode : jsonNodeList) {
                broker.put(QuestionResponseModel.builder()
                        .value(jsonNode.findValue(KEY_TEXT).textValue())
                        .source(CSV_TYPE)
                        .build());
            }
        }
    }

    //***************************************************************************
    private void fillImages(HashSet<String> links) throws IOException {
        for (String link : links) {
            String imageName = getFileName(link);
            createFileFromUrl(link, imageName);
            String encodeBase = baseImageEncoder(imageName);
            JsonNode jsonNode = dataResourceRepository.getTextFromImageSource(encodeBase);
            broker.put(QuestionResponseModel.builder()
                    .value(jsonNode.textValue())
                    .source(IMAGE_TYPE)
                    .build());
            removeDirFile(imageName);
        }
    }

    //***************************************************************************
}
