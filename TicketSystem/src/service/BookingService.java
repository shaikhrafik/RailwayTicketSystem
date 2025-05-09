package service;

import dao.TicketDao;
import dao.TrainDao;
import models.*;

import java.util.List;

public class BookingService implements Booking {
    private final TrainDao TrainDao = new TrainDao();
    private final TicketDao TicketDao = new TicketDao();

    @Override
    public void bookTicket(Passenger passenger, int trainId) {
        List<Train> trains = TrainDao.getAllTrains();
        for (Train t : trains) {
            if (t.getTrainId() == trainId && t.getAvailableSeats() > 0) {
                boolean booked = TicketDao.bookTicket(passenger, t);
                if (booked) {
                    TrainDao.updateSeat(trainId, t.getAvailableSeats() - 1);
                    System.out.println("Ticket booked successfully!");
                } else {
                    System.out.println("Booking failed.");
                }
                return;
            }
        }
        System.out.println("Train not found or no seats available.");
    }

    @Override
    public void cancelTicket(int ticketId) {
        boolean success = TicketDao.cancelTicket(ticketId);
        if (success) {
            System.out.println("Ticket cancelled successfully.");
        } else {
            System.out.println("Cancellation failed.");
        }
    }

    public void showAvailableTrains() {
        List<Train> trains = TrainDao.getAllTrains();
        for (Train t : trains) {
            System.out.println(t.getTrainId() + " - " + t.getTrainName() + " | " + t.getSource() + " to " + t.getDestination() + " | Seats: " + t.getAvailableSeats());
        }
    }

    public void viewMyBookings(String email) {
        List<Ticket> tickets = TicketDao.getTicketsByPassenger(email);
        if (tickets.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getTicketId() + ", Train: " + ticket.getTrain().getTrainName() +
                    ", From: " + ticket.getTrain().getSource() + ", To: " + ticket.getTrain().getDestination());
        }
    }

    public void addTrain(Train train) {
        boolean success = TrainDao.addTrain(train);
        if (success) {
            System.out.println("Train added successfully.");
        } else {
            System.out.println("Failed to add train.");
        }
    }

    public void viewAllBookings() {
        List<Ticket> tickets = TicketDao.getAllTickets();
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getTicketId() + ", Passenger: " + ticket.getPassenger().getName() +
                    ", Train: " + ticket.getTrain().getTrainName());
        }
    }
}

