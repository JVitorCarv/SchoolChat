package schoolChat.clients.chat;

import schoolChat.models.Message;
import schoolChat.views.Menu;

public class ChatClient {
    public static void execute(String author) {
        Message message;
        do {
            message = Menu.getMessage(author);
            System.out.println(message.toChatFormat());
        } while (!message.getTopic().equalsIgnoreCase("fim"));
    }
}
