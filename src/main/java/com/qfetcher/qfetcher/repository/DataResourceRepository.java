package com.qfetcher.qfetcher.repository;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

public interface DataResourceRepository {
    JsonNode getTextFromImageSource(String imageUrl) throws IOException;

    List<JsonNode> parseCSVFile(String dataType);

    JsonNode parseJsonFile(String dataType) throws IOException;
}
