// ============================================================
// Task 4: Hotel Reservation System
// Author: NAVEEN MANI PANDEY
// Description: Full hotel booking system with OOP design
// ============================================================

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();
        printBanner();

        boolean running = true;
        while (running) {
            System.out.println("\n╔══════ MAIN MENU ══════╗");
            System.out.println("║ 1. View All Rooms      ║");
            System.out.println("║ 2. Search Available    ║");
            System.out.println("║ 3. Book a Room         ║");
            System.out.println("║ 4. View My Booking     ║");
            System.out.println("║ 5. Cancel Booking      ║");
            System.out.println("║ 6. Make Payment        ║");
            System.out.println("║ 7. All Bookings List   ║");
            System.out.println("║ 8. Exit                ║");
            System.out.println("╚═══════════════════════╝");
            System.out.print("Choice: ");

            switch (getIntInput()) {
                case 1: viewAllRooms(); break;
                case 2: searchAvailableRooms(); break;
                case 3: bookRoom(); break;
                case 4: viewBooking(); break;
                case 5: cancelBooking(); break;
                case 6: makePayment(); break;
                case 7: listAllBookings(); break;
                case 8:
                    System.out.println("\nThank you for choosing Hotel NAVEEN!");
                    System.out.println("System by: NAVEEN MANI PANDEY");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void initializeRooms() {
        // Standard rooms (101-105)
        for (int i = 101; i <= 105; i++)
            rooms.add(new Room(i, Room.RoomType.STANDARD, 2500.00));
        // Deluxe rooms (201-203)
        for (int i = 201; i <= 203; i++)
            rooms.add(new Room(i, Room.RoomType.DELUXE, 5000.00));
        // Suites (301-302)
        rooms.add(new Room(301, Room.RoomType.SUITE, 10000.00));
        rooms.add(new Room(302, Room.RoomType.SUITE, 12000.00));
    }

    private static void printBanner() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║            HOTEL NAVEEN - RESERVATION SYSTEM             ║");
        System.out.println("║                  NAVEEN MANI PANDEY                      ║");
        System.out.println("║       Standard | Deluxe | Suite - Your Comfort First     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    private static void viewAllRooms() {
        System.out.println("\n--- ALL ROOMS ---");
        System.out.println("Room No | Type       | Price/Night | Status     | Features");
        System.out.println("--------------------------------------------------------------------------");
        for (Room r : rooms) r.display();
    }

    private static void searchAvailableRooms() {
        System.out.println("\nSearch by type:");
        System.out.println("1. Standard (₹2,500/night)");
        System.out.println("2. Deluxe   (₹5,000/night)");
        System.out.println("3. Suite    (₹10,000+/night)");
        System.out.println("4. All Available");
        System.out.print("Choice: ");
        int choice = getIntInput();

        System.out.println("\n--- AVAILABLE ROOMS ---");
        boolean found = false;
        for (Room r : rooms) {
            if (!r.isAvailable()) continue;
            boolean match = (choice == 4)
                    || (choice == 1 && r.getType() == Room.RoomType.STANDARD)
                    || (choice == 2 && r.getType() == Room.RoomType.DELUXE)
                    || (choice == 3 && r.getType() == Room.RoomType.SUITE);
            if (match) { r.display(); found = true; }
        }
        if (!found) System.out.println("No rooms available for selected type.");
    }

    private static void bookRoom() {
        System.out.println("\n--- BOOK A ROOM ---");
        System.out.print("Enter Guest Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Enter Room Number: ");
        int roomNo = getIntInput();

        Room selected = findRoom(roomNo);
        if (selected == null) { System.out.println("Room not found."); return; }
        if (!selected.isAvailable()) { System.out.println("Room " + roomNo + " is already booked."); return; }

        System.out.print("Enter Check-In Date (YYYY-MM-DD): ");
        LocalDate checkIn = parseDate(scanner.nextLine().trim());
        System.out.print("Enter Check-Out Date (YYYY-MM-DD): ");
        LocalDate checkOut = parseDate(scanner.nextLine().trim());

        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            System.out.println("Invalid dates. Check-out must be after check-in.");
            return;
        }

        Booking b = new Booking(name, phone, selected, checkIn, checkOut);
        selected.setAvailable(false);
        bookings.add(b);

        System.out.println("\n✓ Booking Confirmed!");
        b.displayBooking();
        System.out.println("\nPlease complete payment to confirm your stay.");
    }

    private static void viewBooking() {
        System.out.print("\nEnter Booking ID (e.g. BK1001): ");
        String id = scanner.nextLine().trim().toUpperCase();
        Booking b = findBooking(id);
        if (b == null) { System.out.println("Booking not found."); return; }
        b.displayBooking();
    }

    private static void cancelBooking() {
        System.out.print("\nEnter Booking ID to cancel: ");
        String id = scanner.nextLine().trim().toUpperCase();
        Booking b = findBooking(id);
        if (b == null) { System.out.println("Booking not found."); return; }
        if (b.isCancelled()) { System.out.println("This booking is already cancelled."); return; }

        System.out.print("Are you sure you want to cancel " + id + "? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) {
            b.cancel();
            System.out.println("Booking " + id + " has been cancelled. Room is now available.");
            if (b.isPaid()) System.out.println("Refund of ₹" + b.getTotalAmount() + " will be processed within 5-7 days.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }

    private static void makePayment() {
        System.out.print("\nEnter Booking ID to pay: ");
        String id = scanner.nextLine().trim().toUpperCase();
        Booking b = findBooking(id);
        if (b == null) { System.out.println("Booking not found."); return; }
        if (b.isPaid()) { System.out.println("Payment already completed for this booking."); return; }
        if (b.isCancelled()) { System.out.println("Cannot pay for a cancelled booking."); return; }

        System.out.printf("Total Amount Due: ₹%.2f%n", b.getTotalAmount());
        System.out.println("Select Payment Method:");
        System.out.println("1. Credit/Debit Card");
        System.out.println("2. UPI / Net Banking");
        System.out.println("3. Cash");
        System.out.print("Choice: ");
        int method = getIntInput();
        String[] methods = {"Credit/Debit Card", "UPI / Net Banking", "Cash"};
        String chosen = (method >= 1 && method <= 3) ? methods[method - 1] : "Cash";

        System.out.println("Processing payment via " + chosen + "...");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        b.markPaid();
        System.out.println("✓ Payment Successful! Amount: ₹" + b.getTotalAmount());
        System.out.println("Booking " + id + " is fully confirmed. Enjoy your stay at Hotel NAVEEN!");
    }

    private static void listAllBookings() {
        System.out.println("\n--- ALL BOOKINGS ---");
        if (bookings.isEmpty()) { System.out.println("No bookings yet."); return; }
        for (Booking b : bookings) b.displayBooking();
        System.out.printf("Total Bookings: %d%n", bookings.size());
    }

    private static Room findRoom(int roomNo) {
        for (Room r : rooms) if (r.getRoomNumber() == roomNo) return r;
        return null;
    }

    private static Booking findBooking(String id) {
        for (Booking b : bookings) if (b.getBookingId().equalsIgnoreCase(id)) return b;
        return null;
    }

    private static LocalDate parseDate(String s) {
        try { return LocalDate.parse(s); }
        catch (DateTimeParseException e) { return null; }
    }

    private static int getIntInput() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Enter a valid number: "); }
        }
    }
}
