package schoolChat.clients;

import schoolChat.clients.announcement.AnnouncementClient;
import schoolChat.clients.chat.ChatClient;
import schoolChat.views.Menu;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Menu.printWelcome();
        String author = Menu.getIdentification();

        int mode = 0;
        while (mode >= 0) {
            mode = Menu.getMode();
            if (mode < 0) {
                Menu.disconnectWarning();
            } else if (mode == 1) {
                System.out.println("We're still implementing announcements...");
                AnnouncementClient.execute();
            } else if (mode == 2) {
                System.out.println("We're still implementing chat...");
                ChatClient.execute(author);
            }
        }
    }
}
