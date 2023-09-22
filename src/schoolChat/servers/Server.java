package schoolChat.servers;

import schoolChat.models.Message;
import schoolChat.models.Serialization;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {
    private final String author;
    private final InetAddress group;
    private final MulticastSocket sendSocket;
    private MulticastSocket listenSocket;

    public Server(MulticastSocket sendSocket, InetAddress group, String author) {
        this.author = author;
        this.group = group;
        this.sendSocket = sendSocket;
    }

    public Server(MulticastSocket sendSocket, MulticastSocket listenSocket, InetAddress group, String author) {
        this.author = author;
        this.group = group;
        this.sendSocket = sendSocket;
        this.listenSocket = listenSocket;
    }

    public String getAuthor() {
        return author;
    }

    public InetAddress getGroup() {
        return group;
    }

    public void sendMessage(String topic, String content) throws IOException {
        Message message = new Message(this.author, topic, content);
        this.sendMessage(message);
    }

    public void sendMessage(Message message) throws IOException {
        byte[] data = Serialization.serializeObject(message);
        sendSocket.send(new DatagramPacket(data, data.length, this.group, 4321));
    }
}
