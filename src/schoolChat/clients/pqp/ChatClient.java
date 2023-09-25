package schoolChat.clients.pqp;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void execute(String author) throws IOException, InterruptedException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 12345);
        } catch (ConnectException e) {
            System.out.println("Could not connect. Please try again later.");
        }

        if (socket != null) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Runnable sendMessageRunnable = new SendMessage(objectOutputStream, author);
            Runnable receiveMessageRunnable = new ReceiveMessage(objectInputStream);

            Thread sendThread = new Thread(sendMessageRunnable);
            Thread receiveThread = new Thread(receiveMessageRunnable);

            sendThread.start();
            receiveThread.start();

            sendThread.join();
            receiveThread.join();
        }
    }
}