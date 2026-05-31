// ============================================================
// Task 2: Stock Trading Platform
// Author: NAVEEN MANI PANDEY
// ============================================================

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private String ownerName;
    // symbol -> [quantity, avgBuyPrice]
    private HashMap<String, double[]> holdings = new HashMap<>();

    public Portfolio(String ownerName) {
        this.ownerName = ownerName;
    }

    public void addHolding(String symbol, int qty, double price) {
        if (holdings.containsKey(symbol)) {
            double[] current = holdings.get(symbol);
            double totalQty = current[0] + qty;
            double avgPrice = ((current[0] * current[1]) + (qty * price)) / totalQty;
            holdings.put(symbol, new double[]{totalQty, avgPrice});
        } else {
            holdings.put(symbol, new double[]{qty, price});
        }
    }

    public boolean removeHolding(String symbol, int qty) {
        if (!holdings.containsKey(symbol)) return false;
        double[] current = holdings.get(symbol);
        if (current[0] < qty) return false;
        current[0] -= qty;
        if (current[0] == 0) holdings.remove(symbol);
        return true;
    }

    public int getQuantity(String symbol) {
        return holdings.containsKey(symbol) ? (int) holdings.get(symbol)[0] : 0;
    }

    public double getAvgPrice(String symbol) {
        return holdings.containsKey(symbol) ? holdings.get(symbol)[1] : 0;
    }

    public void displayPortfolio(HashMap<String, Stock> market) {
        System.out.println("\n--- PORTFOLIO OF " + ownerName.toUpperCase() + " ---");
        if (holdings.isEmpty()) {
            System.out.println("No holdings.");
            return;
        }
        System.out.printf("%-8s %-6s %-12s %-12s %-12s %-12s%n",
                "Symbol", "Qty", "Avg Buy", "Curr Price", "P&L", "P&L %");
        System.out.println("----------------------------------------------------------------------");

        double totalInvested = 0, totalCurrent = 0;
        for (Map.Entry<String, double[]> entry : holdings.entrySet()) {
            String sym = entry.getKey();
            double qty = entry.getValue()[0];
            double avgBuy = entry.getValue()[1];
            double currPrice = market.containsKey(sym) ? market.get(sym).getPrice() : avgBuy;
            double invested = qty * avgBuy;
            double current = qty * currPrice;
            double pnl = current - invested;
            double pnlPct = (pnl / invested) * 100;

            System.out.printf("%-8s %-6.0f ₹%-11.2f ₹%-11.2f %s₹%-10.2f %.2f%%%n",
                    sym, qty, avgBuy, currPrice,
                    pnl >= 0 ? "+" : "-", Math.abs(pnl), pnlPct);
            totalInvested += invested;
            totalCurrent += current;
        }
        System.out.println("----------------------------------------------------------------------");
        double totalPnl = totalCurrent - totalInvested;
        System.out.printf("Total Invested : ₹%.2f%n", totalInvested);
        System.out.printf("Current Value  : ₹%.2f%n", totalCurrent);
        System.out.printf("Overall P&L    : %s₹%.2f (%.2f%%)%n",
                totalPnl >= 0 ? "+" : "-", Math.abs(totalPnl),
                totalInvested > 0 ? (totalPnl / totalInvested) * 100 : 0);
    }

    public HashMap<String, double[]> getHoldings() { return holdings; }
    public String getOwnerName() { return ownerName; }
}
