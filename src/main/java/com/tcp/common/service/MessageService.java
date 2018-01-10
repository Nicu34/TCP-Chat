package com.tcp.common.service;

import com.tcp.common.domain.Message;

import java.util.concurrent.CompletableFuture;

public interface MessageService {
    CompletableFuture<Message> sendMessage(Message message);
}
