// ============================================================
// Task 4: Hotel Reservation System
// Author: NAVEEN MANI PANDEY
// ============================================================

public class Room {
    public enum RoomType { STANDARD, DELUXE, SUITE }

    private int roomNumber;
    private RoomType type;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int roomNumber, RoomType type, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public RoomType getType() { return type; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public String getTypeLabel() {
        switch (type) {
            case STANDARD: return "Standard";
            case DELUXE:   return "Deluxe";
            case SUITE:    return "Suite";
            default:       return "Unknown";
        }
    }

    public String getFeatures() {
        switch (type) {
            case STANDARD: return "Single Bed, Wi-Fi, TV, AC";
            case DELUXE:   return "Double Bed, Wi-Fi, TV, AC, Mini Bar, City View";
            case SUITE:    return "King Bed, Wi-Fi, Smart TV, AC, Jacuzzi, Lounge, Sea View";
            default:       return "Basic amenities";
        }
    }

    public void display() {
        System.out.printf("Room %-4d | %-10s | ₹%-8.2f/night | %-10s | %s%n",
                roomNumber, getTypeLabel(), pricePerNight,
                isAvailable ? "AVAILABLE" : "BOOKED",
                getFeatures());
    }
}
