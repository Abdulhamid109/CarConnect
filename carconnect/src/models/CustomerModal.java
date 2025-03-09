package models;
import java.time.LocalDateTime;

public class CustomerModal {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private String password;
    private LocalDateTime registrationDate;

    public CustomerModal(){
        this.registrationDate = LocalDateTime.now();
    }

    public CustomerModal(int customerID, String firstName, String lastName, String email,
                    String phoneNumber, String address, String userName, String password,
                    LocalDateTime registrationDate){
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.registrationDate = registrationDate;

    }

    public int getCustomerID(){
        return customerID;
    }
    public void  setCustomerID(int customerID){
        this.customerID = customerID;
    }
    public  String getFirstName(){
         return  firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
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
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
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
    public LocalDateTime getRegistrationDate(){
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate){
        this.registrationDate = registrationDate;
    }

    public boolean authenticate(String inputPassword){
        return  this.password != null && this.password.equals(inputPassword);
    }
    public void DisplayCustomerDetails() {
        System.out.println("---------------------------------------------------");
        System.out.println("Customer ID: " + this.customerID);
        System.out.println("First Name: " + this.firstName);
        System.out.println("Last Name: " + this.lastName);
        System.out.println("Email: " + this.email);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Address: " + this.address);
        System.out.println("Username: " + this.userName);
        System.out.println("Password: " + this.password); 
        System.out.println("Registration Date: " + this.registrationDate);
        System.out.println("---------------------------------------------------");
    }
}
