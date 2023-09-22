package schoolChat.servers;

import schoolChat.models.Message;
import schoolChat.views.Menu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class AnnouncementServer extends Server {
    public AnnouncementServer(MulticastSocket socket, InetAddress group, String author) {
        super(socket, group, author);
    }

    public static void main(String[] args) throws IOException {
        MulticastSocket socket = new MulticastSocket();
        InetAddress group = InetAddress.getByName("230.0.0.0");
        AnnouncementServer server = new AnnouncementServer(socket, group, "School Announcements");

        server.sendMessage("Internal", "Server is starting");

        Message message;
        do {
            Menu.endServerHint(server.getAuthor());
            message = Menu.getMessage(server.getAuthor());
            server.sendMessage(message);
        } while (!message.getTopic().equalsIgnoreCase("end"));

        server.sendMessage("Internal", "Server is shutting down");

        Menu.terminateServerWarning(server.getAuthor());
        socket.close();
    }
}
