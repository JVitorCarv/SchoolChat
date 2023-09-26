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
        multicastGroup = InetAddress.getByName("239.255.255.250");
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
            byte[] messageBytes = Serialization.serializeObject(message);

            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, multicastGroup, multicastPort);

            multicastSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeClientOutputStream(ObjectOutputStream objectOutputStream) {
        clientObjectOutputStreams.remove(objectOutputStream);
    }
}
