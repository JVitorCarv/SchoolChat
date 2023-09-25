package schoolChat.servers.chat;

import schoolChat.models.Message;
import schoolChat.views.Menu;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static List<ObjectOutputStream> clientObjectOutputStreams = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Menu.startServerNotice("Chat Server");

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
        for (ObjectOutputStream objectOutputStream : clientObjectOutputStreams) {
            try {
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeClientOutputStream(ObjectOutputStream objectOutputStream) {
        clientObjectOutputStreams.remove(objectOutputStream);
    }
}
