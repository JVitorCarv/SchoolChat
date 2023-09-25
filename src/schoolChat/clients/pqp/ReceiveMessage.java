package schoolChat.clients.pqp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import schoolChat.models.Message;
import schoolChat.models.Serialization;
import schoolChat.views.Menu;

public class ReceiveMessage implements Runnable {
    private MulticastSocket multicastSocket;
    private InetAddress multicastGroup;
    private int multicastPort;

    public ReceiveMessage(MulticastSocket multicastSocket, InetAddress multicastGroup, int multicastPort) {
        this.multicastGroup = multicastGroup;
        this.multicastPort = multicastPort;
        this.multicastSocket = multicastSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    this.multicastSocket.receive(packet);
                } catch (IOException e) {
                    break;
                }

                Object receivedObject = null;
                try {
                    receivedObject = Serialization.deserializeObject(packet.getData());
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                if (receivedObject instanceof Message message) {
                    if (message.getAuthor().equalsIgnoreCase("Chat Server")) {
                        System.out.println(message.toServerFormat());
                    } else {
                        System.out.println(message.toChatFormat());
                    }
                } else {
                    Menu.unknownTypeError();
                }
            }
        } finally {
            if (multicastSocket != null && !multicastSocket.isClosed()) {
                multicastSocket.close();
            }
        }
    }
}
