package schoolChat.clients.announcement;

import schoolChat.models.Message;
import schoolChat.models.Serialization;
import schoolChat.views.Menu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class ReceiveMessage implements Runnable {
    private final MulticastSocket socket;

    public ReceiveMessage(MulticastSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[1024];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                this.socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Object receivedObject = null;
            try {
                receivedObject = Serialization.deserializeObject(packet.getData());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (receivedObject instanceof Message message) {
                System.out.println(message.toChatFormat());
            } else {
                Menu.unknownTypeError();
            }
        }
    }
}
