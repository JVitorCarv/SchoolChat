package schoolChat.clients.chat;

import schoolChat.clients.announcement.ReceiveMessage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class ChatClient {
    public static void execute(String author) throws IOException, InterruptedException {
        MulticastSocket socket = new MulticastSocket(4322);
        InetAddress ia = InetAddress.getByName("230.0.0.0");
        InetSocketAddress group = new InetSocketAddress(ia, 4322);
        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

        socket.joinGroup(group, ni);

        Thread receiveMessageThread = new Thread(new ReceiveMessage(socket));
        receiveMessageThread.start();

        Thread chatInputThread = new Thread(new ChatInput(author, socket, ia, 4322));
        chatInputThread.start();

        receiveMessageThread.join();
        chatInputThread.join();
    }
}
