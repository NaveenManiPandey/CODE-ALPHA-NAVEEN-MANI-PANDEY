// ============================================================
// Task 2: Stock Trading Platform
// Author: NAVEEN MANI PANDEY
// Description: Simulated stock trading with OOP design
// ============================================================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StockTradingPlatform {

    private static HashMap<String, Stock> market = new HashMap<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Portfolio portfolio;
    private static double walletBalance;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("       STOCK TRADING PLATFORM - NAVEEN MANI PANDEY         ");
        System.out.println("============================================================");

        // Initialize market stocks
        market.put("TCS",    new Stock("TCS",    "Tata Consultancy Services", 3850.00));
        market.put("INFY",   new Stock("INFY",   "Infosys Limited",           1520.00));
        market.put("RELIANCE",new Stock("RELIANCE","Reliance Industries",     2940.00));
        market.put("HDFC",   new Stock("HDFC",   "HDFC Bank",                 1680.00));
        market.put("WIPRO",  new Stock("WIPRO",  "Wipro Limited",              490.00));
        market.put("AIRTEL", new Stock("AIRTEL", "Bharti Airtel",             1215.00));

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter starting wallet balance (₹): ");
        walletBalance = getDoubleInput();

        portfolio = new Portfolio(name);
        System.out.println("\nWelcome, " + name + "! Your balance: ₹" + walletBalance);

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Refresh Market Prices");
            System.out.println("7. Exit");
            System.out.print("Choice: ");

            switch (getIntInput()) {
                case 1: displayMarket(); break;
                case 2: buyStock(); break;
                case 3: sellStock(); break;
                case 4: portfolio.displayPortfolio(market); System.out.printf("Wallet Balance: ₹%.2f%n", walletBalance); break;
                case 5: showTransactions(); break;
                case 6: refreshMarket(); break;
                case 7:
                    System.out.println("\nThank you for trading! - NAVEEN MANI PANDEY");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void displayMarket() {
        System.out.println("\n============================================================");
        System.out.println("                    LIVE MARKET DATA                       ");
        System.out.println("============================================================");
        System.out.printf("%-6s %-25s %-12s %-15s%n", "SYM", "COMPANY", "PRICE", "CHANGE");
        System.out.println("------------------------------------------------------------");
        for (Stock s : market.values()) s.display();
        System.out.println("------------------------------------------------------------");
        System.out.printf("Your Wallet Balance: ₹%.2f%n", walletBalance);
    }

    private static void buyStock() {
        displayMarket();
        System.out.print("Enter stock symbol to BUY: ");
        String sym = scanner.nextLine().trim().toUpperCase();
        if (!market.containsKey(sym)) { System.out.println("Stock not found."); return; }

        Stock s = market.get(sym);
        System.out.printf("Current price of %s: ₹%.2f%n", sym, s.getPrice());
        System.out.print("Enter quantity to buy: ");
        int qty = getIntInput();

        double total = qty * s.getPrice();
        if (total > walletBalance) {
            System.out.printf("Insufficient balance! Need ₹%.2f, have ₹%.2f%n", total, walletBalance);
            return;
        }

        walletBalance -= total;
        portfolio.addHolding(sym, qty, s.getPrice());
        Transaction t = new Transaction("BUY", sym, qty, s.getPrice());
        transactions.add(t);
        System.out.printf("Bought %d shares of %s at ₹%.2f. Total: ₹%.2f%n", qty, sym, s.getPrice(), total);
        System.out.printf("Remaining Balance: ₹%.2f%n", walletBalance);
    }

    private static void sellStock() {
        portfolio.displayPortfolio(market);
        System.out.print("Enter stock symbol to SELL: ");
        String sym = scanner.nextLine().trim().toUpperCase();

        int owned = portfolio.getQuantity(sym);
        if (owned == 0) { System.out.println("You don't own any shares of " + sym); return; }

        System.out.printf("You own %d shares. Enter quantity to sell: ", owned);
        int qty = getIntInput();

        if (qty > owned) { System.out.println("You don't have that many shares."); return; }

        Stock s = market.get(sym);
        double total = qty * s.getPrice();
        portfolio.removeHolding(sym, qty);
        walletBalance += total;
        Transaction t = new Transaction("SELL", sym, qty, s.getPrice());
        transactions.add(t);
        System.out.printf("Sold %d shares of %s at ₹%.2f. Received: ₹%.2f%n", qty, sym, s.getPrice(), total);
        System.out.printf("New Balance: ₹%.2f%n", walletBalance);
    }

    private static void showTransactions() {
        System.out.println("\n--- TRANSACTION HISTORY ---");
        if (transactions.isEmpty()) { System.out.println("No transactions yet."); return; }
        for (Transaction t : transactions) t.display();
    }

    private static void refreshMarket() {
        for (Stock s : market.values()) s.updatePrice();
        System.out.println("Market prices refreshed!");
        displayMarket();
    }

    private static int getIntInput() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Enter a valid number: "); }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Enter a valid number: "); }
        }
    }
}
