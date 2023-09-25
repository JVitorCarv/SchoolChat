package schoolChat.clients.announcement;

import schoolChat.clients.Client;

import java.io.IOException;
import java.net.*;

public class AnnouncementClient extends Client {

    public static void execute() throws IOException, InterruptedException {
        MulticastSocket socket = Client.getMulticastSocket(4321, "230.0.0.0");

        Thread receiveMessageThread = new Thread(new ReceiveMessage(socket));
        receiveMessageThread.start();

        Thread userInputThread = new Thread(new UserInput(socket));
        userInputThread.start();

        receiveMessageThread.join();
        userInputThread.join();
    }
}
