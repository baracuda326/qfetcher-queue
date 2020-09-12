package com.qfetcher.qfetcher.repository.impl;

import com.qfetcher.qfetcher.exception.DataResourceException;
import com.qfetcher.qfetcher.repository.DataManifestRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.qfetcher.qfetcher.utils.Constants.*;
import static com.qfetcher.qfetcher.utils.FilesClearUtils.removeDirFile;
import static com.qfetcher.qfetcher.utils.ParseDataUtils.getFormatFile;

@Repository
public class DataManifestRepositoryImpl implements DataManifestRepository {
    //****************************************************************************

    /**
     * @param filter
     * @param fileName
     * @return Map<String, HashSet < String>> map: store key (csv,json or image)
     * and values set unique links from example.dat file
     * @throws IOException
     * @throws DataResourceException
     */
    @Override
    public Map<String, HashSet<String>> getUrlsFromDat(List<String> filter, String fileName) {
        Map<String, HashSet<String>> map = createResource(filter);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String readLine = "";
            while (true) {
                if (!((readLine = bufferedReader.readLine()) != null)) break;
                String format = getFormatFile(readLine);
                if (IMAGE_FORMAT_LIST.contains(format)) format = IMAGE_TYPE;
                if (map.containsKey(format)) {
                    map.get(format).add(readLine);
                }
            }
        } catch (IOException e) {
            throw new DataResourceException("No valid data resource!");
        }
        removeDirFile(fileName);
        return map;
    }

    /**
     * @param filter
     * @return Map<String, HashSet < String>> map: store key (csv,json or image)
     * * and values set unique links from example.dat file
     */
    //****************************************************************************
    private Map<String, HashSet<String>> createResource(List<String> filter) {
        Map<String, HashSet<String>> map = new HashMap<>();
        if (filter.isEmpty()) {
            FORMAT_LIST.forEach(key -> map.put(key, new HashSet<>()));
        } else {
            filter.forEach(key -> map.put(key, new HashSet<>()));
        }
        return map;
    }
    //****************************************************************************
}
