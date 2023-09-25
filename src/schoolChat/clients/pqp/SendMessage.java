package schoolChat.clients.pqp;

import schoolChat.models.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.SocketException;

public class SendMessage implements Runnable {
    private ObjectOutputStream objectOutputStream;
    private String author;
    private MulticastSocket socket;

    public SendMessage(MulticastSocket socket, ObjectOutputStream objectOutputStream, String author) {
        this.socket = socket;
        this.objectOutputStream = objectOutputStream;
        this.author = author;
    }

    public int sendMessage(Message message) {
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            return 0;
        } catch (IOException e) {
            System.out.println("Connection has been reset. Please try again.");
            return -1;
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            sendMessage(new Message(author, "connect"));

            while (true) {
                System.out.print("$ ");
                String userMessage = userInput.readLine();

                if (userMessage.equalsIgnoreCase("exit")) {
                    Message exitMessage = new Message(author, "exit");
                    sendMessage(exitMessage);
                    socket.close();
                    break;
                }
                Message message = new Message(author, userMessage);
                sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
