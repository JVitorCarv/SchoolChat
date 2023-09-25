package schoolChat.servers.chat;

import schoolChat.models.Message;
import schoolChat.models.Operational;
import schoolChat.models.Serialization;
import schoolChat.servers.Server;
import schoolChat.views.Menu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ChatServer extends Server {
    public ChatServer(MulticastSocket sendSocket, MulticastSocket listenSocket, InetAddress group, String author) {
        super(sendSocket, listenSocket, group, author);
    }

    public static void main(String[] args) throws IOException {
        int port = 4322;
        MulticastSocket sendSocket = new MulticastSocket(port);
        MulticastSocket listenSocket = new MulticastSocket(port);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        ChatServer server = new ChatServer(sendSocket, listenSocket, group, "Chat Server");

        server.sendMessage("Hallo", "World", port);

        while (true) {
            byte[] buffer = new byte[1024];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                listenSocket.receive(packet);
            } catch (IOException e) {
                break;
            }

            Object receivedObject;
            try {
                receivedObject = Serialization.deserializeObject(packet.getData());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (receivedObject instanceof Message message) {
                if (message.getOperation().equals(Operational.DISCONNECTED)) {
                    //server.sendMessage(new Message("ChatServer", message.getAuthor() + "disconnected"));
                } else {
                    //server.sendMessage(message);
                }
                System.out.println(message.toChatFormat());
            } else {
                Menu.unknownTypeError();
            }
        }

        listenSocket.close();
        sendSocket.close();
    }
}
