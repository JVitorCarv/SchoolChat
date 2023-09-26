package schoolChat.clients;

import schoolChat.clients.announcement.AnnouncementClient;
import schoolChat.clients.chat.ChatClient;
import schoolChat.views.Menu;
import schoolChat.views.MenuGUI;

import java.io.IOException;

public class ClientSelect {
    public static void main(String[] args) throws IOException, InterruptedException {
        String author = MenuGUI.getIdentification();
        author = author.trim();

        int mode = 0;

        if (author.isBlank() || author.equalsIgnoreCase("exit")) {
            mode = -1;
        }

        while (mode >= 0) {
            mode = MenuGUI.getMode("Select a mode", "School Chat");
            if (mode == 1) {
                AnnouncementClient.execute();
                Menu.printLine();
            } else if (mode == 2) {
                ChatClient.execute(author);
                Menu.printLine();
            }
        }
        Menu.disconnectWarning();
    }
}
