package schoolChat.servers.announcement;

import schoolChat.models.Message;
import schoolChat.servers.Server;
import schoolChat.views.Menu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class AnnouncementServer extends Server {
    public AnnouncementServer(MulticastSocket socket, InetAddress group, String author) {
        super(socket, group, author);
    }

    public static void main(String[] args) throws IOException {
        int port = 4321;
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        AnnouncementServer server = new AnnouncementServer(socket, group, "School Announcements");

        server.sendMessage("Internal", "Server is starting", port);

        Message message;
        do {
            Menu.endServerHint(server.getAuthor());
            message = Menu.getMessage(server.getAuthor());
            server.sendMessage(message, port);
        } while (!message.getContent().equalsIgnoreCase("end"));

        server.sendMessage("Internal", "Server is shutting down", port);

        Menu.terminateServerWarning(server.getAuthor());
        socket.close();
    }
}
