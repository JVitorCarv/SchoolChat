package schoolChat.clients;

import schoolChat.models.Message;
import schoolChat.models.Serialization;

import java.io.IOException;
import java.net.*;

public class AnnouncementClient {
    public static void execute() throws IOException, ClassNotFoundException {
        MulticastSocket socket = new MulticastSocket(4321);
        InetAddress ia = InetAddress.getByName("230.0.0.0");
        InetSocketAddress group = new InetSocketAddress(ia, 4321);
        NetworkInterface ni = NetworkInterface.getByInetAddress(ia);

        socket.joinGroup(group, ni);

        while (true) {
            byte[] buffer = new byte[1024];

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            Object receivedObject = Serialization.deserializeObject(packet.getData());

            if (receivedObject instanceof Message) {
                Message message = (Message) receivedObject;
                System.out.println(message.toChatFormat());
            } else {
                System.out.println("[Error] Received an object of unknown type");
            }
        }
    }
}
