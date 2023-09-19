package schoolChat.views;

import schoolChat.models.Message;

import java.util.Scanner;

public class Menu {
    private static final Scanner sc = new Scanner(System.in);

    public static void printWelcome() {
        System.out.println("== CESAR SCHOOL ==");
    }

    public static void printAscii() {
        String asciiArt = "                              .*                 ,                              \n" +
                "                .,//////,      .*/(**       */(/,.      .//////,                \n" +
                "          *////////      *////                    /////      ,////////.         \n" +
                "      (/////////*       /////////,            .//////////       ///////////     \n" +
                "   /////////////         //////////////////////////////.        .////////////*  \n" +
                " /////////////////             *////////////////*.            .////////////////,\n" +
                "*//////////////////////.                                 ,(/////////////////////\n" +
                " ///////////////////////////////////////////////////////////////////////////////\n" +
                " ///////////////////////////////////////////////////////////////////////////// \n" +
        "    *///////////////////////////////////////////////////////////////////////.   \n" +
                "        ////////////////////////////////////////////////////////////////,       \n" +
                "             ,/////////////////////////////////////////////////////             \n" +
                "                      .*/////////////////////////////////,                     \n" +
                "                                                                                \n" +
                "                              ,/                                         */.    \n" +
                "                              ,/.                                        */.    \n" +
                "   ///////       .//////.     ,//////*        ,//////.     *///          */.    \n" +
                "  //.           //            ,/*    //     .//      ///.//,             */.    \n" +
                "     *////      /(            ,/.    //     ,/.       ////.       //     */.    \n" +
                "  //*  .//,      //*  ,//     ,/.    //      *//,  *//,   ///. .*//      ,/* .   ";

        System.out.println(asciiArt);
    }
    public static String getIdentification() {
        System.out.print("Please identify yourself: ");
        return sc.nextLine();
    }

    public static int getMode() {
        System.out.println("1 - Announcements");
        System.out.println("2 - Chat");
        System.out.println("Or press anything else to quit");
        System.out.print(">> ");

        String response = sc.nextLine();

        int convertedValue;
        try {
            convertedValue = Integer.parseInt(response);
            if (convertedValue < 1 || convertedValue > 2) {
                convertedValue = -1;
            }
        } catch(NumberFormatException e) {
            convertedValue = -1;
        }
        return convertedValue;
    }

    public static Message getMessage(String author) {
        System.out.print("Topic: ");
        String topic = sc.nextLine();

        System.out.print("Content: ");
        String content = sc.nextLine();

        return new Message(author, topic, content);
    }
}
