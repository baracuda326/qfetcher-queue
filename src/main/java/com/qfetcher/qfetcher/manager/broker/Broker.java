package com.qfetcher.qfetcher.manager.broker;

import com.qfetcher.qfetcher.model.QuestionResponseModel;

import java.util.concurrent.BlockingQueue;

public interface Broker {
    QuestionResponseModel take() throws InterruptedException;

    void put(QuestionResponseModel data);

    BlockingQueue<QuestionResponseModel> getQueue();
}
