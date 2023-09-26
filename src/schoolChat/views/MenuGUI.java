package schoolChat.views;

import javax.swing.*;

public class MenuGUI {
    public static String getIdentification() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Type your username: ");
        JTextField textField = new JTextField(20);

        panel.add(label);
        panel.add(textField);

        String[] options = new String[]{"Send"};

        ImageIcon icon = new ImageIcon("school_logo.png");

        int option = JOptionPane.showOptionDialog(null, panel, "What is your name?",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);

        if (option == JOptionPane.OK_OPTION) {
            return textField.getText();
        } else {
            return "exit"; // Return an empty string or handle it as needed
        }
    }


    public static String getMessage(String author) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Type your message: ");
        JTextField textField = new JTextField(20);

        panel.add(label);
        panel.add(textField);

        String[] options = new String[]{"Send"};

        ImageIcon icon = new ImageIcon("school_logo.png");

        int option = JOptionPane.showOptionDialog(null, panel, "Chat - " + author,
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options, options[0]);

        if (option == JOptionPane.OK_OPTION) {
            return textField.getText();
        } else {
            return "exit"; // Return an empty string or handle it as needed
        }
    }
}
