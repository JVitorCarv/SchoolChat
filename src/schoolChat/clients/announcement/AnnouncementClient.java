package schoolChat.clients.announcement;

import schoolChat.clients.Client;

import java.io.IOException;
import java.net.*;

public class AnnouncementClient extends Client {
    public AnnouncementClient(MulticastSocket listenSocket, InetAddress ia, InetSocketAddress group, NetworkInterface ni) {
        super(listenSocket, ia, group, ni);
    }

    public static void execute() throws IOException, InterruptedException {
        MulticastSocket socket = new MulticastSocket(4321);
        InetAddress ia = InetAddress.getByName("230.0.0.0");
        InetSocketAddress group = new InetSocketAddress(ia, 4321);
        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

        socket.joinGroup(group, ni);

        Thread receiveMessageThread = new Thread(new ReceiveMessage(socket));
        receiveMessageThread.start();

        Thread userInputThread = new Thread(new UserInput(socket));
        userInputThread.start();

        receiveMessageThread.join();
        userInputThread.join();
    }
}
