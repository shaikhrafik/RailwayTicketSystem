package models;

public class Ticket {
    private int ticketId;
    private Passenger passenger;
    private Train train;

    public Ticket(int ticketId, Passenger passenger, Train train) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.train = train;
    }

    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public Train getTrain() { return train; }
    public void setTrain(Train train) { this.train = train; }
}

