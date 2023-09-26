package schoolChat.clients;

import schoolChat.clients.announcement.AnnouncementClient;
import schoolChat.clients.chat.ChatClient;
import schoolChat.views.Menu;
import schoolChat.views.MenuGUI;

import java.io.IOException;

public class ClientSelect {
    public static void main(String[] args) throws IOException, InterruptedException {
        String author = MenuGUI.getIdentification();

        int mode = 0;
        while (mode >= 0) {
            mode = MenuGUI.getMode("Select a mode", "School Chat");
            if (mode < 0) {
                Menu.disconnectWarning();
            } else if (mode == 1) {
                AnnouncementClient.execute();
            } else if (mode == 2) {
                ChatClient.execute(author);
            }
        }
    }
}
