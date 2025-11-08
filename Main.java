import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AirlineSystem system = new AirlineSystem();
        int choice;

        do {
            System.out.println("\n===== Airline Reservation System =====");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scan.nextInt();
            scan.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    system.showFlights();
                    break;
                case 2:
                    System.out.print("Enter Flight Number: ");
                    String fNum = scan.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter Passenger ID: ");
                    String id = scan.nextLine();
                    system.bookFlight(fNum, name, id);
                    break;
                case 3:
                    System.out.print("Enter Passenger ID to cancel: ");
                    String cancelId = scan.nextLine();
                    system.cancelBooking(cancelId);
                    break;
                case 4:
                    system.showAllBookings();
                    break;
                case 5:
                    System.out.println("👋 Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }
}