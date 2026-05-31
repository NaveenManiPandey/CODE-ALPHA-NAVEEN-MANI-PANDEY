// ============================================================
// Task 3: Artificial Intelligence Chatbot
// Author: NAVEEN MANI PANDEY
// Description: Java-based AI chatbot with NLP & rule-based logic
// ============================================================

import java.util.Scanner;
import java.util.Date;

public class AIChatbot {

    private static final String BOT_NAME = "JavaBot";
    private static Scanner scanner = new Scanner(System.in);
    private static int messageCount = 0;

    public static void main(String[] args) {
        printBanner();

        System.out.print("Please enter your name: ");
        String userName = scanner.nextLine().trim();
        if (userName.isEmpty()) userName = "User";

        System.out.println("\n" + BOT_NAME + ": Hello, " + userName + "! I'm " + BOT_NAME +
                ", your AI assistant built by NAVEEN MANI PANDEY.");
        System.out.println(BOT_NAME + ": Type 'help' to see what I can do, or 'exit' to quit.\n");

        boolean chatting = true;
        while (chatting) {
            System.out.print("[" + userName + "]: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(BOT_NAME + ": Please type something. I'm listening! 👂");
                continue;
            }

            messageCount++;

            // Exit condition
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")
                    || input.equalsIgnoreCase("bye")) {
                System.out.println(BOT_NAME + ": Goodbye, " + userName + "! We exchanged "
                        + messageCount + " messages. Take care! 👋");
                System.out.println(BOT_NAME + ": Session ended at: " + new Date());
                System.out.println("\n    Developed by: NAVEEN MANI PANDEY");
                chatting = false;
                continue;
            }

            // Get NLP response
            String response = NLPProcessor.processInput(input);

            // Add typing delay simulation
            System.out.print(BOT_NAME + " is typing");
            for (int i = 0; i < 3; i++) {
                try { Thread.sleep(300); } catch (InterruptedException e) {}
                System.out.print(".");
            }
            System.out.println();

            System.out.println(BOT_NAME + ": " + response + "\n");

            // Periodic message
            if (messageCount % 5 == 0) {
                System.out.println(BOT_NAME + ": [TIP] We've had " + messageCount
                        + " exchanges! You can ask me about Java, AI, math, fun facts, and more.\n");
            }
        }
        scanner.close();
    }

    private static void printBanner() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║          AI CHATBOT - NAVEEN MANI PANDEY                 ║");
        System.out.println("║          Powered by Rule-Based NLP Engine                ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println("  Session started: " + new Date());
        System.out.println();
    }
}
