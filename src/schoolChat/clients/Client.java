package schoolChat.clients;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class Client {
    public static MulticastSocket getMulticastSocket(int port, String host) throws IOException {
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress ia = InetAddress.getByName(host);
        InetSocketAddress group = new InetSocketAddress(ia, port);
        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

        socket.joinGroup(group, ni);
        return socket;
    }
}
