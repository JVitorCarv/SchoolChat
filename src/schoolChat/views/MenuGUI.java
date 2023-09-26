package schoolChat.views;

import javax.swing.*;

public class MenuGUI {
    public static String getInfo(String labelText, String titleText) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField(20);

        panel.add(label);
        panel.add(textField);

        String[] options = new String[]{"Send"};

        ImageIcon icon = new ImageIcon("school_logo.png");

        int option = JOptionPane.showOptionDialog(null, panel, titleText,
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);

        if (option == JOptionPane.OK_OPTION) {
            return textField.getText();
        } else {
            return "exit";
        }
    }
    public static String getIdentification() {
        return MenuGUI.getInfo("Type your username: ", "What is your name?");
    }

    public static String getMessage(String author) {
        return MenuGUI.getInfo("Type your message: ", "Chat - " + author);
    }

    public static int getMode(String labelText, String titleText) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(labelText);

        String[] options = new String[]{"Announcements", "Chat"};

        ImageIcon icon = new ImageIcon("school_logo.png");

        int option = JOptionPane.showOptionDialog(
                null,
                label,
                titleText,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                options,
                options[0]
        );

        if (option == 0) {
            return 1;
        } else if (option == 1) {
            return 2;
        } else {
            return -1;
        }
    }
}
