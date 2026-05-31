// ============================================================
// Task 4: Hotel Reservation System
// Author: NAVEEN MANI PANDEY
// ============================================================

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private static int counter = 1000;

    private String bookingId;
    private String guestName;
    private String guestPhone;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalAmount;
    private boolean isPaid;
    private boolean isCancelled;

    public Booking(String guestName, String guestPhone, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.bookingId = "BK" + (++counter);
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        this.totalAmount = nights * room.getPricePerNight();
        this.isPaid = false;
        this.isCancelled = false;
    }

    public String getBookingId() { return bookingId; }
    public String getGuestName() { return guestName; }
    public Room getRoom() { return room; }
    public boolean isCancelled() { return isCancelled; }
    public boolean isPaid() { return isPaid; }

    public void markPaid() { this.isPaid = true; }
    public void cancel() {
        this.isCancelled = true;
        room.setAvailable(true);
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public void displayBooking() {
        System.out.println("  ┌─────────────────────────────────────────────────┐");
        System.out.printf( "  │ Booking ID  : %-34s│%n", bookingId);
        System.out.printf( "  │ Guest Name  : %-34s│%n", guestName);
        System.out.printf( "  │ Phone       : %-34s│%n", guestPhone);
        System.out.printf( "  │ Room        : %-34s│%n", room.getRoomNumber() + " (" + room.getTypeLabel() + ")");
        System.out.printf( "  │ Check-In    : %-34s│%n", checkIn);
        System.out.printf( "  │ Check-Out   : %-34s│%n", checkOut);
        System.out.printf( "  │ Nights      : %-34s│%n", getNights());
        System.out.printf( "  │ Total Amt   : ₹%-33.2f│%n", totalAmount);
        System.out.printf( "  │ Payment     : %-34s│%n", isPaid ? "PAID ✓" : "PENDING");
        System.out.printf( "  │ Status      : %-34s│%n", isCancelled ? "CANCELLED ✗" : "CONFIRMED ✓");
        System.out.println("  └─────────────────────────────────────────────────┘");
    }

    public double getTotalAmount() { return totalAmount; }
}
