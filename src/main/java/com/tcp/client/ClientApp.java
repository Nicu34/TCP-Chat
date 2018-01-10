package com.tcp.client;

import com.tcp.common.domain.User;
import com.tcp.client.service.MessageServiceImpl;
import com.tcp.client.tcp.TcpClient;
import com.tcp.client.ui.Console;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public void runClient(String host, Integer port) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your nickname: ");
        String nickname = scanner.nextLine();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient();
        Console ui = new Console(new MessageServiceImpl(executorService, tcpClient), new User(nickname, host, port));
        try {
            ui.runConsole();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdownNow();
        }
    }
}
