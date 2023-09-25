package schoolChat.clients.chat;

import schoolChat.clients.announcement.ReceiveMessage;

import java.io.*;
import java.net.*;

public class ChatClient {
    public static void execute(String author) throws IOException, InterruptedException {
        MulticastSocket multicastSocket = new MulticastSocket(6789);
        InetAddress ia = InetAddress.getByName("224.0.0.1");
        InetSocketAddress multicastGroup = new InetSocketAddress(ia, 6789);
        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

        multicastSocket.joinGroup(multicastGroup, ni);

        Socket socket = null;
        try {
            socket = new Socket("localhost", 12345);
        } catch (ConnectException e) {
            System.out.println("Could not connect. Please try again later.");
        }

        if (socket != null) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            Runnable sendMessageRunnable = new SendMessage(multicastSocket, objectOutputStream, author);
            Runnable receiveMessageRunnable = new ReceiveMessage(multicastSocket);

            Thread sendThread = new Thread(sendMessageRunnable);
            Thread receiveThread = new Thread(receiveMessageRunnable);

            sendThread.start();
            receiveThread.start();

            sendThread.join();
            receiveThread.join();
        }
    }
}