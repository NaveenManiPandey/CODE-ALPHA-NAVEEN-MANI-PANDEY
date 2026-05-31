# Task 4: Hotel Reservation System

**Author: NAVEEN MANI PANDEY**

## Description
A complete Java-based Hotel Reservation System with OOP design. Supports room browsing, booking, cancellation, and payment simulation for 3 room types: Standard, Deluxe, and Suite.

## Features
- 10 pre-configured rooms (Standard, Deluxe, Suite)
- Search available rooms by type
- Book rooms with guest details and date range
- View booking details with formatted receipt
- Cancel bookings with refund simulation
- Payment simulation (Card, UPI, Cash)
- Auto-generated booking IDs
- Night calculation and total billing

## How to Run

### Compile
```bash
cd src
javac Room.java Booking.java HotelReservationSystem.java
```

### Run
```bash
java HotelReservationSystem
```

## Room Categories
| Type | Rooms | Price/Night | Features |
|------|-------|-------------|----------|
| Standard | 101-105 | ₹2,500 | Single Bed, Wi-Fi, TV, AC |
| Deluxe | 201-203 | ₹5,000 | Double Bed, Wi-Fi, TV, AC, Mini Bar, City View |
| Suite | 301-302 | ₹10,000-₹12,000 | King Bed, Smart TV, AC, Jacuzzi, Lounge, Sea View |

## Class Structure
| Class | Responsibility |
|-------|---------------|
| `Room` | Room data, type, price, availability |
| `Booking` | Guest booking with dates, billing, status |
| `HotelReservationSystem` | Main controller with all menu operations |

## Technologies Used
- Java SE
- OOP (Classes, Enums, Encapsulation)
- ArrayList
- LocalDate (date handling)
- ChronoUnit (night calculation)
