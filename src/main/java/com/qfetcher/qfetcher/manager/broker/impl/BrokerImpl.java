package com.qfetcher.qfetcher.manager.broker.impl;

import com.qfetcher.qfetcher.manager.broker.Broker;
import com.qfetcher.qfetcher.model.QuestionResponseModel;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class BrokerImpl implements Broker {
    private BlockingQueue<QuestionResponseModel> blockingQueue;

    public BrokerImpl() {
        this.blockingQueue = new LinkedBlockingDeque<>();
    }

    //***************************************************************************
    @Override
    public QuestionResponseModel take() {
        return blockingQueue.remove();
    }

    //***************************************************************************
    @Override
    public void put(QuestionResponseModel data) {
        blockingQueue.add(data);
    }

    //***************************************************************************
    @Override
    public BlockingQueue<QuestionResponseModel> getQueue() {
        return blockingQueue;
    }
    //***************************************************************************
}
