package com.tcp.server.service;

import com.tcp.common.domain.Message;
import com.tcp.common.service.MessageService;
import lombok.AllArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private ExecutorService executorService;

    @Override
    public CompletableFuture<Message> sendMessage(Message message) {
        return CompletableFuture.supplyAsync(() -> message, executorService);
    }
}
