package com.qfetcher.qfetcher.manager.consumer.impl;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.qfetcher.qfetcher.manager.broker.Broker;
import com.qfetcher.qfetcher.manager.consumer.Consumer;
import com.qfetcher.qfetcher.model.QuestionResponseModel;
import com.qfetcher.qfetcher.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
        ExecutorService es = Executors.newSingleThreadExecutor();
        TimeLimiter timeLimiter = SimpleTimeLimiter.create(es);
        try {
            timeLimiter.callWithTimeout(() -> {
                        while (!queue.isEmpty()) {
                            response.add(queue.take());
                        }
                        return ResponseModel.builder()
                                .questions(response)
                                .build();
                    }
                    , 30L, TimeUnit.SECONDS);
        } catch (TimeoutException | ExecutionException ex) {
            return ResponseModel.builder()
                    .questions(response)
                    .build();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return ResponseModel.builder()
                .questions(response)
                .build();
    }
}

