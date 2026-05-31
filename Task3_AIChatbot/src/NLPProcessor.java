// ============================================================
// Task 3: Artificial Intelligence Chatbot
// Author: NAVEEN MANI PANDEY
// Description: Rule-based NLP chatbot with keyword matching
// ============================================================

import java.util.*;

public class NLPProcessor {

    private static final Map<String[], String> knowledgeBase = new LinkedHashMap<>();

    static {
        // Greetings
        knowledgeBase.put(new String[]{"hello", "hi", "hey", "greetings", "howdy"},
                "Hello! I'm JavaBot, your AI assistant created by NAVEEN MANI PANDEY. How can I help you today?");

        // Farewells
        knowledgeBase.put(new String[]{"bye", "goodbye", "exit", "quit", "see you"},
                "Goodbye! It was great chatting with you. Have a wonderful day! - NAVEEN MANI PANDEY");

        // Name / Identity
        knowledgeBase.put(new String[]{"your name", "who are you", "what are you"},
                "I am JavaBot, an AI Chatbot developed by NAVEEN MANI PANDEY as part of a Java programming project.");

        // How are you
        knowledgeBase.put(new String[]{"how are you", "how r u", "are you fine"},
                "I'm functioning at 100% capacity! As an AI, I don't have feelings, but I'm always ready to help. How about you?");

        // Java
        knowledgeBase.put(new String[]{"java", "programming", "code", "coding"},
                "Java is a versatile, object-oriented programming language. It's platform-independent thanks to the JVM. This very chatbot is built in Java by NAVEEN MANI PANDEY!");

        // AI / Machine Learning
        knowledgeBase.put(new String[]{"artificial intelligence", "machine learning", "ai", "ml", "deep learning"},
                "Artificial Intelligence is the simulation of human intelligence by machines. Machine Learning is a subset of AI where systems learn from data to improve over time.");

        // Weather
        knowledgeBase.put(new String[]{"weather", "temperature", "rain", "sunny", "climate"},
                "I don't have access to real-time weather data, but I'd recommend checking a weather app or website like weather.com for current conditions!");

        // Time / Date
        knowledgeBase.put(new String[]{"time", "date", "today", "day"},
                "The current time and date are: " + new java.util.Date().toString());

        // Help
        knowledgeBase.put(new String[]{"help", "assist", "support", "what can you do"},
                "I can help with:\n  • General conversation\n  • Java & programming questions\n  • AI/ML concepts\n  • Math calculations (type: calc <expression>)\n  • General knowledge\n  Just type your question!");

        // Math
        knowledgeBase.put(new String[]{"math", "mathematics", "calculate", "sum", "plus", "minus"},
                "I can do basic calculations! Just type 'calc' followed by an expression, e.g., 'calc 25 + 75'");

        // India
        knowledgeBase.put(new String[]{"india", "lucknow", "delhi", "mumbai", "hindi"},
                "India is a beautiful and diverse country! It's home to over 1.4 billion people and has a rich culture, history, and tradition. Jai Hind!");

        // Fun fact
        knowledgeBase.put(new String[]{"fact", "interesting", "fun", "did you know", "trivia"},
                "Fun Fact: The first computer bug was an actual bug! In 1947, a moth was found stuck in a relay of the Harvard Mark II computer.");

        // Jokes
        knowledgeBase.put(new String[]{"joke", "funny", "laugh", "humor"},
                "Why do Java developers wear glasses? Because they don't C# ! 😄");

        // Thanks
        knowledgeBase.put(new String[]{"thank", "thanks", "thank you", "appreciated"},
                "You're most welcome! Happy to help. If you have more questions, feel free to ask!");

        // About developer
        knowledgeBase.put(new String[]{"naveen", "developer", "creator", "author", "made by"},
                "This chatbot was developed by NAVEEN MANI PANDEY as part of a Java programming internship project. Passionate about coding and AI!");
    }

    public static String processInput(String input) {
        String lower = input.toLowerCase().trim();

        // Calculation feature
        if (lower.startsWith("calc ") || lower.startsWith("calculate ")) {
            return handleCalculation(lower.replaceFirst("calc(ulate)?\\s+", ""));
        }

        // Keyword matching
        for (Map.Entry<String[], String> entry : knowledgeBase.entrySet()) {
            for (String keyword : entry.getKey()) {
                if (lower.contains(keyword.toLowerCase())) {
                    return entry.getValue();
                }
            }
        }

        // Fallback
        return "Hmm, I'm not sure about that. Could you rephrase or ask something else? "
                + "Type 'help' to see what I can assist with!";
    }

    private static String handleCalculation(String expr) {
        try {
            // Simple eval for +, -, *, /
            expr = expr.trim();
            if (expr.contains("+")) {
                String[] parts = expr.split("\\+");
                return expr + " = " + (Double.parseDouble(parts[0].trim()) + Double.parseDouble(parts[1].trim()));
            } else if (expr.contains("-")) {
                String[] parts = expr.split("-");
                return expr + " = " + (Double.parseDouble(parts[0].trim()) - Double.parseDouble(parts[1].trim()));
            } else if (expr.contains("*")) {
                String[] parts = expr.split("\\*");
                return expr + " = " + (Double.parseDouble(parts[0].trim()) * Double.parseDouble(parts[1].trim()));
            } else if (expr.contains("/")) {
                String[] parts = expr.split("/");
                double divisor = Double.parseDouble(parts[1].trim());
                if (divisor == 0) return "Cannot divide by zero!";
                return expr + " = " + (Double.parseDouble(parts[0].trim()) / divisor);
            }
            return "I couldn't parse that expression. Try: calc 10 + 5";
        } catch (Exception e) {
            return "Invalid expression. Example: calc 100 * 3.14";
        }
    }
}
