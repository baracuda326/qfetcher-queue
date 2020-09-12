package com.qfetcher.qfetcher.service.impl;

import com.qfetcher.qfetcher.exception.DataResourceException;
import com.qfetcher.qfetcher.exception.NoValidFieldException;
import com.qfetcher.qfetcher.manager.consumer.Consumer;
import com.qfetcher.qfetcher.model.RequestModel;
import com.qfetcher.qfetcher.model.ResponseModel;
import com.qfetcher.qfetcher.service.DataListenerService;
import com.qfetcher.qfetcher.service.FetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Dmitry Asmalouski
 * @version 2.0
 */
@Service
public class FetcherServiceImpl implements FetcherService {
    private DataListenerService dataListenerService;
    private Consumer consumer;

    @Autowired
    public FetcherServiceImpl(DataListenerService dataListenerService, Consumer consumer) {
        this.dataListenerService = dataListenerService;
        this.consumer = consumer;
    }

//****************************************************************************

    /**
     * @param request RequestModel
     * @return ResponseModel
     * @method checkFields(RequestModel request)
     * checks for field validity RequestModel request
     */
    @Override
    public ResponseModel fetchQuestion(RequestModel request) {
        checkFields(request);
        return parseData(request);
    }

    //***************************************************************************

    /**
     * @param request RequestModel
     * @throws NoValidFieldException Checks for field validity RequestModel request
     */
    private void checkFields(RequestModel request) {
        if (request.getManifest() == null || request.getManifest().trim().isEmpty()
                || request.getFilter() == null)
            throw new NoValidFieldException("There is no valid manifest or filter field!");
    }

    //***************************************************************************

    private ResponseModel parseData(RequestModel request) {
        try {
            dataListenerService.fetchData(request);
        } catch (IOException e) {
            throw new DataResourceException("No valid data resource!");
        }
        return consumer.getResponse();
    }
//***************************************************************************
}
