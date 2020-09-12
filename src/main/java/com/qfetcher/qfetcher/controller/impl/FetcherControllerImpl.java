package com.qfetcher.qfetcher.controller.impl;

import com.qfetcher.qfetcher.controller.FetcherController;
import com.qfetcher.qfetcher.model.RequestModel;
import com.qfetcher.qfetcher.model.ResponseModel;
import com.qfetcher.qfetcher.service.FetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dmitry Asmalouski
 * @version 2.0
 */
@RestController
@RequestMapping
public class FetcherControllerImpl implements FetcherController {
    private FetcherService fetcherService;

    @Autowired
    public FetcherControllerImpl(FetcherService fetcherService) {
        this.fetcherService = fetcherService;
    }

    //****************************************************************************
    @Override
    public ResponseModel fetchQuestion(RequestModel requestModel) {
        return fetcherService.fetchQuestion(requestModel);
    }
    //****************************************************************************
}
