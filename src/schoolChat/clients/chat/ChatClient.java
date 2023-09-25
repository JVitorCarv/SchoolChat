package schoolChat.clients.chat;

import schoolChat.clients.Client;
import schoolChat.clients.runnables.ReceiveMessageRunnable;
import schoolChat.clients.runnables.SendMessageRunnable;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void execute(String author) throws IOException, InterruptedException {
        MulticastSocket multicastSocket = Client.getMulticastSocket(6789, "224.0.0.1");

        Socket socket = null;
        try {
            socket = new Socket("localhost", 12345);
        } catch (ConnectException e) {
            System.out.println("Could not connect. Please try again later.");
        }

        if (socket != null) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            Runnable sendMessageRunnable = new SendMessageRunnable(multicastSocket, objectOutputStream, author);
            Runnable receiveMessageRunnable = new ReceiveMessageRunnable(multicastSocket);

            Thread sendThread = new Thread(sendMessageRunnable);
            Thread receiveThread = new Thread(receiveMessageRunnable);

            sendThread.start();
            receiveThread.start();

            sendThread.join();
            receiveThread.join();
        }
    }
}