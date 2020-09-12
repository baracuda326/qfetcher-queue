package com.qfetcher.qfetcher.manager.consumer.impl;

import com.qfetcher.qfetcher.manager.broker.Broker;
import com.qfetcher.qfetcher.manager.consumer.Consumer;
import com.qfetcher.qfetcher.model.QuestionResponseModel;
import com.qfetcher.qfetcher.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Service
public class ConsumerImpl implements Consumer {
    private BlockingQueue<QuestionResponseModel> queue;
    private Broker broker;

    @Autowired
    public ConsumerImpl(Broker broker) {
        this.queue = broker.getQueue();
    }

    @Override
    public ResponseModel getResponse() {
        List<QuestionResponseModel> response = new ArrayList<>();
        response.addAll(queue);
        queue.clear();
        return ResponseModel.builder()
                .questions(response)
                .build();
    }
}

