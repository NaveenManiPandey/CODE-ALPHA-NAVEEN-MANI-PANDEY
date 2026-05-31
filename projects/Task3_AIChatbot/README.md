# Task 3: Artificial Intelligence Chatbot

**Author: NAVEEN MANI PANDEY**

## Description
A Java-based AI Chatbot that uses rule-based Natural Language Processing (NLP) to respond to user queries. It supports keyword-based intent detection, basic arithmetic evaluation, and smart fallback responses.

## Features
- Rule-based NLP with keyword matching
- Covers topics: greetings, Java, AI/ML, weather, jokes, fun facts, math, India
- Built-in calculator: `calc 50 * 2`
- Typing delay simulation for realistic chat feel
- Message count tracking
- Session timestamp display

## How to Run

### Compile
```bash
cd src
javac NLPProcessor.java AIChatbot.java
```

### Run
```bash
java AIChatbot
```

## Sample Interaction
```
[You]: hello
JavaBot: Hello! I'm JavaBot, your AI assistant created by NAVEEN MANI PANDEY...

[You]: tell me a joke
JavaBot: Why do Java developers wear glasses? Because they don't C# !

[You]: calc 100 + 250
JavaBot: 100 + 250 = 350.0

[You]: bye
JavaBot: Goodbye! Take care!
```

## Supported Commands
| Input | Response |
|-------|----------|
| hello / hi | Greeting |
| who are you | Bot identity |
| java / coding | Java information |
| ai / machine learning | AI explanation |
| fact / trivia | Fun fact |
| joke | Developer joke |
| calc 10 * 5 | Math result |
| help | Full capability list |
| bye / exit | End session |

## Technologies Used
- Java SE
- LinkedHashMap (knowledge base)
- NLP keyword matching
- Thread.sleep (typing simulation)
