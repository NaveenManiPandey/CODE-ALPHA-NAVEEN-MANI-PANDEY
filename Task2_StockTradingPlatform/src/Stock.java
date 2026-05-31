// ============================================================
// Task 2: Stock Trading Platform
// Author: NAVEEN MANI PANDEY
// ============================================================

import java.util.Random;

public class Stock {
    private String symbol;
    private String companyName;
    private double price;
    private double openPrice;
    private double change;
    private double changePercent;
    private Random random = new Random();

    public Stock(String symbol, String companyName, double initialPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = initialPrice;
        this.openPrice = initialPrice;
        this.change = 0;
        this.changePercent = 0;
    }

    // Simulate market fluctuation
    public void updatePrice() {
        double fluctuation = (random.nextDouble() - 0.48) * price * 0.05;
        price = Math.max(1.0, price + fluctuation);
        change = price - openPrice;
        changePercent = (change / openPrice) * 100;
    }

    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public double getPrice() { return price; }
    public double getChange() { return change; }
    public double getChangePercent() { return changePercent; }

    public void display() {
        String trend = change >= 0 ? "▲" : "▼";
        System.out.printf("%-6s %-25s ₹%-10.2f %s %-8.2f (%.2f%%)%n",
                symbol, companyName, price, trend, Math.abs(change), Math.abs(changePercent));
    }
}
