package com.tcp.client.ui;

import com.tcp.common.domain.Message;
import com.tcp.common.domain.User;
import com.tcp.common.service.MessageService;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author nicu
 */

@AllArgsConstructor
public class Console {

    private MessageService messageService;

    private User user;

    public void runConsole() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Boolean exit = false;
//            System.out.println("Give host of the user you want to write: ");
//            String host = scanner.nextLine();
            String host = "localhost";
            System.out.println("Give port of the user you want to write: ");
            Integer port = Integer.parseInt(scanner.nextLine());
            System.out.println("Send message: ");
            String message = scanner.nextLine();

            User receiver = new User("", host, port);

            if (message.equalsIgnoreCase("exit")) {
                messageService.sendMessage(new Message(user, user.getName() + " just disconnected.", receiver));
                break;
            } else {
                messageService.sendMessage(new Message(user, message, receiver));
            }
        }
    }
}
