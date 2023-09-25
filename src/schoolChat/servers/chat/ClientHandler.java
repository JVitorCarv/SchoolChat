package schoolChat.servers.chat;

import schoolChat.models.Message;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ClientHandler(Socket socket, ObjectOutputStream objectOutputStream) {
        this.clientSocket = socket;
        this.objectOutputStream = objectOutputStream;
        try {
            this.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message receivedMessage = (Message) objectInputStream.readObject();

                if (receivedMessage.getContent().equalsIgnoreCase("connect")) {
                    System.out.println(receivedMessage.getAuthor() + " has connected");
                    ChatServer.broadcastMessage(new Message("Chat Server", receivedMessage.getAuthor() + " has connected"));
                    continue;
                }

                if (receivedMessage.getContent().equalsIgnoreCase("exit")) {
                    try {
                        System.out.println(receivedMessage.getAuthor() + " has disconnected");
                        ChatServer.broadcastMessage(new Message("Chat Server", receivedMessage.getAuthor() + " has disconnected"));

                        objectOutputStream.close();
                        objectInputStream.close();
                        clientSocket.close();
                        ChatServer.removeClientOutputStream(objectOutputStream);
                    } catch (EOFException ignored) {}
                    break;
                }

                System.out.println(receivedMessage.toChatFormat());

                ChatServer.broadcastMessage(receivedMessage);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
