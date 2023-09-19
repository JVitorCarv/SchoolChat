package schoolChat.clients.announcement;

import java.io.IOException;
import java.net.*;

public class AnnouncementClient {
    public static void execute() throws IOException {
        MulticastSocket socket = new MulticastSocket(4321);
        InetAddress ia = InetAddress.getByName("230.0.0.0");
        InetSocketAddress group = new InetSocketAddress(ia, 4321);
        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

        socket.joinGroup(group, ni);

        ReceiveMessage receiveMessage = new ReceiveMessage(socket);
        Thread receiveMessageThread = new Thread(receiveMessage);
        receiveMessageThread.start();

        Thread userInputThread = new Thread(new UserInput(socket));
        userInputThread.start();
    }
}
