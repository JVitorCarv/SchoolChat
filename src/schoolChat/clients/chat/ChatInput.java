package schoolChat.clients.chat;

import schoolChat.models.Message;
import schoolChat.models.Operational;
import schoolChat.models.Serialization;
import schoolChat.views.Menu;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ChatInput implements Runnable {
    private final String author;
    private final MulticastSocket socket;
    private final InetAddress group;
    private final int port;

    public ChatInput(String author, MulticastSocket socket, InetAddress group, int port) {
        this.author = author;
        this.socket = socket;
        this.group = group;
        this.port = port;
    }

    public void sendMessage(byte[] buffer) {
        try {
            socket.send(new DatagramPacket(buffer, buffer.length, this.group, this.port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        Menu.endClientHint();
        while (true) {
            byte[] buffer;
            Message message = Menu.getMessage(author);
            try {
                buffer = Serialization.serializeObject(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (message.getContent().equalsIgnoreCase("exit")) {
                Menu.disconnectWarning();
                Message disconnect = new Message(author, Operational.DISCONNECTED);
                try {
                    buffer = Serialization.serializeObject(disconnect);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sendMessage(buffer);
                this.socket.close();
                break;
            } else {
                sendMessage(buffer);
            }
        }
    }
}
