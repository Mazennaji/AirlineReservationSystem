
import java.io.*;
import java.util.*;

public class AirlineSystem {
    private List<Flight> flights = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public AirlineSystem() {
        // Predefined flights
        flights.add(new Flight("FL101", "Paris", 5));
        flights.add(new Flight("FL202", "London", 3));
        flights.add(new Flight("FL303", "New York", 2));
    }

    public void showFlights() {
        System.out.println("\n--- Available Flights ---");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public void bookFlight(String flightNumber, String name, String id) {
        Flight flight = findFlight(flightNumber);
        if (flight == null) {
            System.out.println("❌ Flight not found!");
            return;
        }

        if (flight.bookSeat()) {
            Passenger passenger = new Passenger(name, id);
            Booking booking = new Booking(passenger, flight);
            bookings.add(booking);
            saveBookingToFile(booking);
            System.out.println("✅ Booking successful for " + name);
        } else {
            System.out.println("❌ No seats available!");
        }
    }

    public void cancelBooking(String passengerId) {
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getPassenger().getId().equals(passengerId)) {
                booking.getFlight().cancelSeat();
                iterator.remove();
                System.out.println("✅ Booking cancelled for " + booking.getPassenger().getName());
                return;
            }
        }
        System.out.println("❌ No booking found for that ID!");
    }

    public void showAllBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet!");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }

    private Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    private void saveBookingToFile(Booking booking) {
        try (FileWriter fw = new FileWriter("bookings.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(booking.toString());
        } catch (IOException e) {
            System.out.println("Error saving booking: " + e.getMessage());
        }
    }
}
