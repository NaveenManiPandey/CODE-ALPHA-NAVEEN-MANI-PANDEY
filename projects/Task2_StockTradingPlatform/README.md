# Task 2: Stock Trading Platform

**Author: NAVEEN MANI PANDEY**

## Description
A Java-based simulated stock trading platform using OOP principles. Users can view live (simulated) market data, buy/sell stocks, track portfolio performance, and view transaction history.

## Features
- Live market simulation with 6 Indian stocks (TCS, INFY, RELIANCE, HDFC, WIPRO, AIRTEL)
- Buy/Sell stock operations with wallet balance management
- Portfolio tracking with P&L (Profit & Loss) calculation
- Full transaction history
- Market price refresh with random fluctuations
- OOP design: Stock, Portfolio, Transaction classes

## How to Run

### Compile
```bash
cd src
javac Stock.java Transaction.java Portfolio.java StockTradingPlatform.java
```

### Run
```bash
java StockTradingPlatform
```

## Class Structure
| Class | Responsibility |
|-------|---------------|
| `Stock` | Stores stock info, simulates price changes |
| `Transaction` | Records buy/sell operations with timestamp |
| `Portfolio` | Manages user holdings and P&L |
| `StockTradingPlatform` | Main app controller |

## Technologies Used
- Java SE
- OOP (Classes, Encapsulation, Methods)
- HashMap, ArrayList
- Random (market simulation)
- LocalDateTime (timestamps)
