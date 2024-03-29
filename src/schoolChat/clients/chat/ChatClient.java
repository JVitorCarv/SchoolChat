package schoolChat.clients.chat;

import schoolChat.clients.Client;
import schoolChat.clients.runnables.ReceiveMessageRunnable;
import schoolChat.clients.runnables.SendMessageRunnable;
import schoolChat.views.Menu;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void execute(String author) throws IOException, InterruptedException {
        MulticastSocket multicastSocket = Client.getMulticastSocket(6789, "239.255.255.250");

        Socket socket = Client.getSocket(12345, "localhost");

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