package com.tcp.client.service;

import com.google.gson.Gson;
import com.tcp.common.domain.Data;
import com.tcp.common.domain.Message;
import com.tcp.common.domain.User;
import com.tcp.client.tcp.TcpClient;
import com.tcp.common.service.MessageService;
import com.tcp.common.utils.MenuEnum;
import lombok.AllArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private ExecutorService executorService;

    private TcpClient tcpClient;

    @Override
    public CompletableFuture<Message> sendMessage(Message message) {
        Gson gson = new Gson();

        return CompletableFuture.supplyAsync(() -> {
            User receiver = message.getReceiver();
            Data request = new Data(MenuEnum.MESSAGE, gson.toJson(message));
            Data response = tcpClient.sendAndReceive(request, receiver.getHost(), receiver.getPort());

            return gson.fromJson(response.getDataRequest(), Message.class);
        }, executorService);
    }
}
