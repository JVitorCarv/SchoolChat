package schoolChat.clients;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class Client {
    private final MulticastSocket listenSocket;
    private final InetAddress ia;
    private final InetSocketAddress group;
    private final NetworkInterface ni;

    public Client(MulticastSocket listenSocket, InetAddress ia, InetSocketAddress group, NetworkInterface ni) {
        this.listenSocket = listenSocket;
        this.ia = ia;
        this.group = group;
        this.ni = ni;
    }

}
