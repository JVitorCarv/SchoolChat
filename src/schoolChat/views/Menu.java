package schoolChat.views;

import schoolChat.models.Message;

import java.util.Scanner;

public class Menu {
    public static void printWelcome() {
        System.out.println("== CESAR SCHOOL ==");
    }

    public static void printAscii() {
        String asciiArt = """
                                              .*                 ,                             \s
                                .,//////,      .*/(**       */(/,.      .//////,               \s
                          *////////      *////                    /////      ,////////.        \s
                      (/////////*       /////////,            .//////////       ///////////    \s
                   /////////////         //////////////////////////////.        .////////////* \s
                 /////////////////             *////////////////*.            .////////////////,
                *//////////////////////.                                 ,(/////////////////////
                 ///////////////////////////////////////////////////////////////////////////////
                 /////////////////////////////////////////////////////////////////////////////\s
                    *///////////////////////////////////////////////////////////////////////.  \s
                        ////////////////////////////////////////////////////////////////,      \s
                             ,/////////////////////////////////////////////////////            \s
                                      .*/////////////////////////////////,                    \s
                                                                                               \s
                                              ,/                                         */.   \s
                                              ,/.                                        */.   \s
                   ///////       .//////.     ,//////*        ,//////.     *///          */.   \s
                  //.           //            ,/*    //     .//      ///.//,             */.   \s
                     *////      /(            ,/.    //     ,/.       ////.       //     */.   \s
                  //*  .//,      //*  ,//     ,/.    //      *//,  *//,   ///. .*//      ,/* .  \s""";

        System.out.println(asciiArt);
    }
    public static String getIdentification() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please identify yourself: ");
        return sc.nextLine();
    }

    public static int getMode() {
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Topic: ");
        String topic = sc.nextLine();

        System.out.print("Content: ");
        String content = sc.nextLine();

        return new Message(author, topic, content);
    }

    public static void endClientHint() {
        System.out.println("Type 'exit' to leave");
    }

    public static void endServerHint(String server) {
        System.out.println("[" + server + "] Enter announcement (type 'end' in 'Topic' to quit): ");
    }

    public static void terminateServerWarning(String server) {
        System.out.println("[" + server + "]  Terminating server...");
    }

    public static void unknownTypeError() {
        System.out.println("(Error) Received an object of unknown type");
    }

    public static void disconnectWarning() {
        System.out.println("== Disconnecting... ==");
    }
}
