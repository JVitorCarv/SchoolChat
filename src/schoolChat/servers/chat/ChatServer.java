package schoolChat.servers.chat;

import schoolChat.models.Message;
import schoolChat.models.Serialization;
import schoolChat.views.Menu;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static List<ObjectOutputStream> clientObjectOutputStreams = new ArrayList<>();
    private static MulticastSocket multicastSocket;
    private static InetAddress multicastGroup;
    private static int multicastPort = 6789;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Menu.startServerNotice("Chat Server");

        multicastSocket = new MulticastSocket(multicastPort);
        multicastGroup = InetAddress.getByName("224.0.0.1");
        multicastSocket.joinGroup(multicastGroup);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            clientObjectOutputStreams.add(objectOutputStream);

            Thread clientThread = new ClientHandler(clientSocket, objectOutputStream);
            clientThread.start();
        }
    }

    public static void broadcastMessage(Message message) {
        try {
            // Convert the message to bytes
            byte[] messageBytes = Serialization.serializeObject(message);

            // Create a DatagramPacket for multicast
            DatagramPacket packet = new DatagramPacket(
                    messageBytes,
                    messageBytes.length,
                    multicastGroup, // Multicast group InetAddress
                    multicastPort   // Multicast port
            );

            // Send the message to the multicast group
            multicastSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeClientOutputStream(ObjectOutputStream objectOutputStream) {
        clientObjectOutputStreams.remove(objectOutputStream);
    }
}
