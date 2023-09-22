package schoolChat.clients.chat;

import schoolChat.clients.announcement.ReceiveMessage;
import schoolChat.clients.announcement.UserInput;
import schoolChat.models.Message;
import schoolChat.views.Menu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class ChatClient {
    public static void execute(String author) throws IOException, InterruptedException {
        MulticastSocket listenSocket = new MulticastSocket(4321);
        MulticastSocket sendSocket = new MulticastSocket(4321);
        InetAddress ia = InetAddress.getByName("230.0.0.0");
        InetSocketAddress group = new InetSocketAddress(ia, 4321);
        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

        listenSocket.joinGroup(group, ni);

        Thread receiveMessageThread = new Thread(new ReceiveMessage(listenSocket));
        receiveMessageThread.start();

        Thread chatInputThread = new Thread(new ChatInput(listenSocket, ia, 4321));
        chatInputThread.start();

        receiveMessageThread.join();
        chatInputThread.join();
    }
}
