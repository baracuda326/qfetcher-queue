package com.qfetcher.qfetcher.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface DataManifestRepository {
    Map<String, HashSet<String>> getUrlsFromDat(List<String> filter, String fileName);
}
