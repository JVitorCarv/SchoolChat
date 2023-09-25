package schoolChat.clients;

import java.io.IOException;
import java.net.*;

public class Client {
    public static MulticastSocket getMulticastSocket(int port, String host) throws IOException {
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress ia = InetAddress.getByName(host);
        InetSocketAddress group = new InetSocketAddress(ia, port);
        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

        socket.joinGroup(group, ni);
        return socket;
    }

    public static Socket getSocket(int port, String host) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("Could not connect. Please try again later.");
        }
        return socket;
    }
}
