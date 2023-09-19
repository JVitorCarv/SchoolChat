package schoolChat.clients.announcement;

import schoolChat.views.Menu;

import java.net.MulticastSocket;
import java.util.Scanner;

public class UserInput implements Runnable {

    private final MulticastSocket socket;

    public UserInput(MulticastSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Menu.endClientHint();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                Menu.disconnectWarning();
                this.socket.close();
                System.exit(0);
            }
        }
    }
}
