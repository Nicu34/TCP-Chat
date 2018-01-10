package com.tcp;

import com.tcp.client.ClientApp;
import com.tcp.server.ServerApp;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatInitializer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give the port you want to use: ");
        Integer servicePort = scanner.nextInt();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        ServerApp serverApp = new ServerApp();
        ClientApp clientApp = new ClientApp();
        executorService.submit(() -> serverApp.runServer("localhost", servicePort));
        executorService.submit(() -> clientApp.runClient("localhost", servicePort));

    }
}
