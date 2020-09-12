package com.qfetcher.qfetcher.service;

import com.qfetcher.qfetcher.model.RequestModel;

import java.io.IOException;

public interface DataListenerService {
    void fetchData(RequestModel request) throws IOException;
}
