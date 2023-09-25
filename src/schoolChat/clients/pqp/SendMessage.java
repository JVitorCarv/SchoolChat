package schoolChat.clients.pqp;

import schoolChat.models.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SendMessage implements Runnable {
    private ObjectOutputStream objectOutputStream;
    private String author;

    public SendMessage(ObjectOutputStream objectOutputStream, String author) {
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

            while (true) {
                System.out.print("$ ");
                String userMessage = userInput.readLine();

                if (userMessage.equalsIgnoreCase("exit")) {
                    Message exitMessage = new Message(author, "exit");
                    sendMessage(exitMessage);
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
