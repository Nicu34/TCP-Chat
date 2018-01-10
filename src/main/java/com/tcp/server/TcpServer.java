package com.tcp.server;


import com.google.gson.Gson;
import com.tcp.common.domain.Data;
import com.tcp.common.domain.Message;
import com.tcp.common.domain.User;
import com.tcp.common.utils.MenuEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

/**
 * @author nicu.
 */
public class TcpServer {
    private ExecutorService executorService;
    private int serverPort;

    private Map<MenuEnum, UnaryOperator<Data>> methodHandlers = new HashMap<>();

    public TcpServer(ExecutorService executorService, int serverPort) {
        this.executorService = executorService;
        this.serverPort = serverPort;
    }

    public void addHandler(MenuEnum action, UnaryOperator<Data> methodHandler) {
        methodHandlers.put(action, methodHandler);
    }

    public void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new ClientHandler(socket));
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            Gson gson = new Gson();
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                Data data = ((Data) inputStream.readObject());
                Message receivedMessage = gson.fromJson(data.getDataRequest(), Message.class);
                User sender = receivedMessage.getSender();
                System.out.println(String.format("%s (%s, %d): %s", sender.getName(), sender.getHost(), sender.getPort(), receivedMessage.getText()));

                UnaryOperator<Data> methodHandler = methodHandlers.get(data.getMenuEnum());
                Data response = methodHandler.apply(data);
                outputStream.writeObject(response);
                outputStream.flush();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
}
