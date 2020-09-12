package com.qfetcher.qfetcher.service;

import com.qfetcher.qfetcher.model.RequestModel;
import com.qfetcher.qfetcher.model.ResponseModel;

public interface FetcherService {
    ResponseModel fetchQuestion(RequestModel requestModel);
}
