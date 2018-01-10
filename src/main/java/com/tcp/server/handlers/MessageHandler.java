package com.tcp.server.handlers;

import com.google.gson.Gson;
import com.tcp.common.domain.Data;
import com.tcp.common.domain.Message;
import com.tcp.common.service.MessageService;
import com.tcp.common.utils.MenuEnum;
import com.tcp.server.TcpServer;

public class MessageHandler {
    private Gson gson = new Gson();

    public void addSendMessageHandler(TcpServer tcpServer, MessageService messageService) {
        tcpServer.addHandler(MenuEnum.MESSAGE, data -> {
            Message receivedMessage = gson.fromJson(data.getDataRequest(), Message.class);
            return new Data(MenuEnum.OK, receivedMessage.getSender().getName() + ": " + receivedMessage.getText());
        });
    }
}
