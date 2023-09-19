package schoolChat.clients;

import schoolChat.views.Menu;
import schoolChat.models.Message;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Menu.printWelcome();
        String author = Menu.getIdentification();

        int mode = Menu.getMode();
        if (mode < 0) {
            System.out.println("Closing client...");
        }
        else if (mode == 1) {
            System.out.println("We're still implementing announcements...");
            AnnouncementClient.execute();
        }
        else if (mode == 2) {
            System.out.println("We're still implementing chat...");
            ChatClient.execute(author);
        }
    }
}
