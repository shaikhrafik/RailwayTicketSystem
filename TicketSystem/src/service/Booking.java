package service;


import models.Passenger;
import models.Train;

public interface Booking {
    void bookTicket(Passenger passenger, int trainId);
    void cancelTicket(int ticketId);
    void showAvailableTrains();
    void viewMyBookings(String email);
    void viewAllBookings();
    void addTrain(Train train);
}

