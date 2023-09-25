package schoolChat.clients.pqp;

import schoolChat.models.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReceiveMessage implements Runnable {
    private ObjectInputStream objectInputStream;

    public ReceiveMessage(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message receivedMessage = (Message) objectInputStream.readObject();
                if (receivedMessage.getAuthor().equalsIgnoreCase("Chat Server")) {
                    System.out.println(receivedMessage.toServerFormat());
                    continue;
                }
                System.out.println(receivedMessage.toChatFormat());
            }
        } catch (IOException | ClassNotFoundException ignored) {}
    }
}
