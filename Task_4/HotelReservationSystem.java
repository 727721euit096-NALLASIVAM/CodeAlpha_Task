import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int reservationIdCounter = 1;

    public static void main(String[] args) {
        initializeRooms();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    searchAvailableRooms(scanner);
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 50.0, true));
        rooms.add(new Room(102, "Double", 80.0, true));
        rooms.add(new Room(103, "Suite", 120.0, true));
    }

    private static void searchAvailableRooms(Scanner scanner) {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }

        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null || !selectedRoom.isAvailable()) {
            System.out.println("Invalid room number or room not available.");
            return;
        }

        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        double totalPrice = selectedRoom.getPrice() * startDate.until(endDate).getDays();

        System.out.println("Total Price: $" + totalPrice);

        // Simulate payment processing
        System.out.print("Confirm payment? (Y/N): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            boolean paymentSuccessful = processPayment(totalPrice);
            if (paymentSuccessful) {
                Reservation reservation = new Reservation(reservationIdCounter++, startDate, endDate, selectedRoom, totalPrice, true);
                reservations.add(reservation);
                selectedRoom.setAvailable(false);

                System.out.println("Reservation successful!");
            } else {
                System.out.println("Payment failed. Reservation canceled.");
            }
        } else {
            System.out.println("Reservation canceled.");
        }
    }

    private static boolean processPayment(double amount) {
        // Simulate payment processing
        // Here, you would integrate with a payment gateway or service
        // For simplicity, we'll just simulate a successful payment
        return true;
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();

        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                System.out.println("\nBooking Details:");
                System.out.println(reservation);
                return;
            }
        }

        System.out.println("Invalid reservation ID.");
    }
}

class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean available;

    public Room(int roomNumber, String type, double price, boolean available) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room: " + roomNumber + ", Type: " + type + ", Price: $" + price + " per night";
    }
}

class Reservation {
    private int reservationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Room room;
    private double totalPrice;
    private boolean paid;

    public Reservation(int reservationId, LocalDate startDate, LocalDate endDate, Room room, double totalPrice, boolean paid) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.totalPrice = totalPrice;
        this.paid = paid;
    }

    public int getReservationId() {
        return reservationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Room getRoom() {
        return room;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                "\nRoom: " + room +
                "\nStart Date: " + startDate +
                "\nEnd Date: " + endDate +
                "\nTotal Price: $" + totalPrice +
                "\nPaid: " + (paid ? "Yes" : "No");
    }
}
