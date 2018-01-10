package com.tcp.server;

import com.tcp.common.service.MessageService;
import com.tcp.server.handlers.MessageHandler;
import com.tcp.server.service.MessageServiceImpl;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author nicu.
 */
public class ServerApp {

    public void runServer(String host, Integer port) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        MessageService messageService = new MessageServiceImpl(executorService);

        MessageHandler messageHandler = new MessageHandler();

        TcpServer tcpServer = new TcpServer(executorService, port);

        messageHandler.addSendMessageHandler(tcpServer, messageService);

        try {
            tcpServer.startServer();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
