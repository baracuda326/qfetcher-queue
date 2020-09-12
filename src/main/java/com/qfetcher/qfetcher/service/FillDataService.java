package com.qfetcher.qfetcher.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

public interface FillDataService {
    void fillData(Map<String, HashSet<String>> map) throws IOException;
}
