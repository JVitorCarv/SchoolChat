package schoolChat.servers;

import schoolChat.views.Menu;
import schoolChat.models.Message;
import schoolChat.models.Serialization;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class AnnouncementServer {
    private MulticastSocket socket;
    private String author;
    private InetAddress group;

    public AnnouncementServer(MulticastSocket socket, InetAddress group, String author) {
        this.socket = socket;
        this.group = group;
        this.author = author;
    }

    public void sendMessage(Message message) throws IOException {
        byte[] data = Serialization.serializeObject(message);

        DatagramPacket packet = new DatagramPacket(data, data.length, this.group, 4321);
        this.socket.send(packet);
    }

    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket();
        InetAddress group = InetAddress.getByName("230.0.0.0");
        AnnouncementServer server = new AnnouncementServer(socket, group, "School Announcements");

        Message message = new Message(server.author, "Internal", "Server is starting");
        server.sendMessage(message);

        do {
            System.out.println("[Announcements] Enter announcement (type 'exit' to quit): ");
            message = Menu.getMessage(server.author);
            server.sendMessage(message);
        } while (!message.getTopic().equalsIgnoreCase("end"));

        message = new Message(server.author, "Internal", "Server is shutting down");
        server.sendMessage(message);

        System.out.println("[Announcements] Terminating server...");
        socket.close();
    }
}
