package models;

import java.time.LocalDateTime;

public class AdminModal {
    private int adminID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;
    private String role;
    private LocalDateTime joinDate;

    public AdminModal() {
        this.joinDate = LocalDateTime.now();
    }

    public AdminModal(int adminID, String firstName, String lastName, String email,
                 String phoneNumber, String username, String password, String role,
                 LocalDateTime joinDate){
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = username;
        this.password = password;
        this.role = role;
        this.joinDate = joinDate;
    }

    public int getAdminID(){
        return adminID;
    }
    public void setAdminID(int adminID){
        this.adminID = adminID;
    }
    public  String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }
    public LocalDateTime getJoinDate(){
        return joinDate;
    }
    public void setJoinDate(LocalDateTime joinDate){
        this.joinDate = joinDate;
    }

    public boolean authenticate(String inputPassword){
        return this.password != null && this.password.equals(inputPassword);
    }

    public void DisplayAdminDetails() {
        System.out.println("---------------------------------------------------");
        System.out.println("Admin ID: " + this.adminID);
        System.out.println("First Name: " + this.firstName);
        System.out.println("Last Name: " + this.lastName);
        System.out.println("Email: " + this.email);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Username: " + this.userName);
        System.out.println("Password: " + this.password); // Consider not displaying the password for security reasons
        System.out.println("Role: " + this.role);
        System.out.println("Join Date: " + this.joinDate);
        System.out.println("---------------------------------------------------");
    }
    
}
