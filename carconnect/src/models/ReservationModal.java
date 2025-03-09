package models;

import java.sql.Date;

public class ReservationModal {
    private int ReservationID;
    private int CustomerID;
    private int VehicleID;
    private Date StartDate;
    private Date EndDate;
    private String Status;

    // Constructor
    public ReservationModal(int ReservationID, int CustomerID, int VehicleID, Date StartDate, Date EndDate, String Status) {
        this.ReservationID = ReservationID;
        this.CustomerID = CustomerID;
        this.VehicleID = VehicleID;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.Status = Status;
    }

    // Getters
    public int getReservationID() {
        return ReservationID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getVehicleID() {
        return VehicleID;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public String getStatus() {
        return Status;
    }

    // Setters
    public void setReservationID(int ReservationID) {
        this.ReservationID = ReservationID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setVehicleID(int VehicleID) {
        this.VehicleID = VehicleID;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    // Display the Reservations
    public void ReservationDisplay() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Reservation ID: " + this.ReservationID);
        System.out.println("Customer ID: " + this.CustomerID);
        System.out.println("Vehicle ID: " + this.VehicleID);
        System.out.println("Reservation Starting Date: " + this.StartDate);
        System.out.println("Reservation Ending Date: " + this.EndDate);
        System.out.println("Status of the Reservation (pending, confirmed, completed, Failed): " + this.Status);
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
