package models;

public class Admin extends User {
    private String role;

    public Admin() {}

    public Admin(String name, int age, String contactNo, String email, String role) {
        super(name, age, contactNo, email);
        this.role = role;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public void displayInfo() {
        System.out.println("Admin: " + name + ", Role: " + role + ", Contact: " + contactNo + ", Email: " + email);
    }
}

