package schoolChat.clients.chat;

import schoolChat.models.Message;
import schoolChat.models.Serialization;
import schoolChat.views.Menu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ChatInput implements Runnable {
    private final MulticastSocket socket;
    private final InetAddress group;
    private final int port;

    public ChatInput(MulticastSocket socket, InetAddress group, int port) {
        this.socket = socket;
        this.group = group;
        this.port = port;
    }

    @Override
    public void run() {
        Menu.endClientHint();
        while (true) {
            byte[] buffer;
            Message message = Menu.getMessage("Author");
            try {
                buffer = Serialization.serializeObject(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                socket.send(new DatagramPacket(buffer, buffer.length, this.group, this.port));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (message.getContent().equalsIgnoreCase("exit")) {
                Menu.disconnectWarning();
                this.socket.close();
                break;
            }
        }
    }
}
