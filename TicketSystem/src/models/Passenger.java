package models;

public class Passenger extends User {
    private int passengerId;

    public Passenger() {}

    public Passenger(String name, int age, String contactNo, String email, int passengerId) {
        super(name, age, contactNo, email);
        this.passengerId = passengerId;
    }

    public int getPassengerId() { return passengerId; }
    public void setPassengerId(int passengerId) { this.passengerId = passengerId; }

    @Override
    public void displayInfo() {
        System.out.println("Passenger: " + name + ", Age: " + age + ", Contact: " + contactNo + ", Email: " + email);
    }
}


