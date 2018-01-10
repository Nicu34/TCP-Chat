package com.tcp.client.tcp;

import com.tcp.common.domain.Data;
import com.tcp.common.utils.MenuEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TcpClient {

    public Data sendAndReceive(Data request, String serviceHost, Integer servicePort) {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try (Socket socket = new Socket(serviceHost, servicePort)) {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
            Data response = ((Data) inputStream.readObject());
            if (response.getMenuEnum().equals(MenuEnum.OK)) {
                System.out.println("Message sent successfully.");
                return response;
            } else {
                System.out.println("Message was not sent.");
                throw new RuntimeException(response.getDataRequest());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
