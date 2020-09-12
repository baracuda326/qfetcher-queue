package com.qfetcher.qfetcher.manager.consumer.impl;

import com.qfetcher.qfetcher.manager.broker.Broker;
import com.qfetcher.qfetcher.manager.consumer.Consumer;
import com.qfetcher.qfetcher.model.QuestionResponseModel;
import com.qfetcher.qfetcher.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

/**
 * @author Dmitry Asmalouski
 * @version 2.0
 */
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
        int hardTimeout = 1; // seconds
        final int[] count = {30};
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                while (!queue.isEmpty()) {
                    if (count[0] <= 0) return;
                    try {
                        response.add(queue.take());
                    } catch (InterruptedException e) {
                        response.addAll(queue);
                    }
                    count[0]--;
                }
            }
        };
        new Timer(true).schedule(task, hardTimeout);
        return ResponseModel.builder()
                .questions(response)
                .build();
    }
}

