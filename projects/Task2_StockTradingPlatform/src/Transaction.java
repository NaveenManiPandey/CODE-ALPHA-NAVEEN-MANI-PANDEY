// ============================================================
// Task 2: Stock Trading Platform
// Author: NAVEEN MANI PANDEY
// ============================================================

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type; // BUY or SELL
    private String stockSymbol;
    private int quantity;
    private double pricePerShare;
    private double totalAmount;
    private String timestamp;

    public Transaction(String type, String stockSymbol, int quantity, double pricePerShare) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.totalAmount = quantity * pricePerShare;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getType() { return type; }
    public String getStockSymbol() { return stockSymbol; }
    public int getQuantity() { return quantity; }
    public double getPricePerShare() { return pricePerShare; }
    public double getTotalAmount() { return totalAmount; }

    public void display() {
        System.out.printf("[%s] %s  %-6s  Qty: %-5d  @₹%-10.2f  Total: ₹%.2f%n",
                timestamp, type, stockSymbol, quantity, pricePerShare, totalAmount);
    }
}
