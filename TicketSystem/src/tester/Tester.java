package tester;
import models.*;
import service.BookingService;
import dao.PassengerDao;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingService bookingService = new BookingService();

        System.out.println("Login as:\n1. Passenger\n2. Admin");
        System.out.print("Enter choice: ");
        int userType = sc.nextInt();
        sc.nextLine(); 
        if (userType == 1) {
            // Passenger Login
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter your contact number: ");
            String contact = sc.nextLine();
            System.out.print("Enter your email: ");
            String email = sc.nextLine();

            // Create Passenger and fetch/set ID from DB
            Passenger passenger = new Passenger(name, age, contact, email, 0);
            PassengerDao passengerDao = new PassengerDao();
            int passengerId = passengerDao.saveOrFetchPassenger(passenger);
            if (passengerId == -1) {
                System.out.println("Login failed. Could not save/fetch passenger.");
                return;
            }
            passenger.setPassengerId(passengerId);

            // Passenger menu
            while (true) {
                System.out.println("\nPassenger Menu:");
                System.out.println("1. View Trains");
                System.out.println("2. Book Ticket");
                System.out.println("3. Cancel Ticket");
                System.out.println("4. View My Bookings");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        bookingService.showAvailableTrains();
                        break;
                    case 2:
                        System.out.print("Enter Train ID to book: ");
                        int trainId = sc.nextInt();
                        bookingService.bookTicket(passenger, trainId);
                        break;
                    case 3:
                        System.out.print("Enter Ticket ID to cancel: ");
                        int ticketId = sc.nextInt();
                        bookingService.cancelTicket(ticketId);
                        break;
                    case 4:
                        bookingService.viewMyBookings(passenger.getEmail());
                        break;
                    case 5:
                        System.out.println("Thank you, " + passenger.getName() + "! Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } else if (userType == 2) {
            // Admin Login
            System.out.print("Enter admin name: ");
            String name = sc.nextLine();
            System.out.print("Enter contact number: ");
            String contact = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            Admin admin = new Admin(name, 0, contact, email, "Administrator");

            while (true) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. View Trains");
                System.out.println("2. Add Train");
                System.out.println("3. View All Bookings");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        bookingService.showAvailableTrains();
                        break;
                    case 2:
                        System.out.print("Enter Train Name: ");
                        String trainName = sc.nextLine();
                        System.out.print("Enter Source: ");
                        String source = sc.nextLine();
                        System.out.print("Enter Destination: ");
                        String destination = sc.nextLine();
                        System.out.print("Enter Available Seats: ");
                        int seats = sc.nextInt();
                        Train train = new Train();
                        train.setTrainName(trainName);
                        train.setSource(source);
                        train.setDestination(destination);
                        train.setAvailableSeats(seats);
                        bookingService.addTrain(train);
                        break;
                    case 3:
                        bookingService.viewAllBookings();
                        break;
                    case 4:
                        System.out.println("Goodbye, Admin " + admin.getName());
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } else {
            System.out.println("Invalid login type.");
        }
    }
}
