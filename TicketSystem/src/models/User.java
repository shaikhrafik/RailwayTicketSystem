package models;

public abstract class User {
    protected String name;
    protected int age;
    protected String contactNo;
    protected String email;

    public User() {}

    public User(String name, int age, String contactNo, String email) {
        this.name = name;
        this.age = age;
        this.contactNo = contactNo;
        this.email = email;
    }

    public abstract void displayInfo();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
