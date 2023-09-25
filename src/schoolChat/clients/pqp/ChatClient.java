package schoolChat.clients.pqp;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void execute(String author) throws IOException, InterruptedException {
        MulticastSocket multicastSocket = new MulticastSocket(6789);
        InetAddress multicastGroup = InetAddress.getByName("224.0.0.1");
        multicastSocket.joinGroup(multicastGroup);

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
            Runnable receiveMessageRunnable = new ReceiveMessage(multicastGroup, 6789);

            Thread sendThread = new Thread(sendMessageRunnable);
            Thread receiveThread = new Thread(receiveMessageRunnable);

            sendThread.start();
            receiveThread.start();

            sendThread.join();
            receiveThread.join();
        }
    }
}