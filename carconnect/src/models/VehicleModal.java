package models;

public class VehicleModal {
    private int VehicleID;
    private String Model;
    private String Make;
    private int Year;
    private String Color;
    private String RegistrationNumber;
    private Boolean Availability;
    private Double DailyRate;

    // Constructor
    public VehicleModal(int VehicleID, String Model, String Make, int Year, String Color, String RegistrationNumber, Boolean Availability, Double DailyRate) {
        this.VehicleID = VehicleID;
        this.Model = Model;
        this.Make = Make;
        this.Year = Year;
        this.Color = Color;
        this.RegistrationNumber = RegistrationNumber;
        this.Availability = Availability;
        this.DailyRate = DailyRate;
    }

    // Getters
    public int getVehicleID() {
        return VehicleID;
    }

    public String getModel() {
        return Model;
    }

    public String getMake() {
        return Make;
    }

    public int getYear() {
        return Year;
    }

    public String getColor() {
        return Color;
    }

    public String getRegistrationNumber() {
        return RegistrationNumber;
    }

    public Boolean getAvailability() {
        return Availability;
    }

    public Double getDailyRate() {
        return DailyRate;
    }

    // Setters
    public void setVehicleID(int VehicleID) {
        this.VehicleID = VehicleID;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public void setMake(String Make) {
        this.Make = Make;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public void setRegistrationNumber(String RegistrationNumber) {
        this.RegistrationNumber = RegistrationNumber;
    }

    public void setAvailability(Boolean Availability) {
        this.Availability = Availability;
    }

    public void setDailyRate(Double DailyRate) {
        this.DailyRate = DailyRate;
    }

    // Display the Vehicle Details
    public void VehicleDisplay() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Vehicle ID: " + this.VehicleID);
        System.out.println("Vehicle Model: " + this.Model);
        System.out.println("Vehicle Brand: " + this.Make);
        System.out.println("Vehicle Manufacturing Year: " + this.Year);
        System.out.println("Vehicle Color: " + this.Color);
        System.out.println("Vehicle Registration Number: " + this.RegistrationNumber);
        System.out.println("Vehicle Availability: " + (this.Availability ? "Available" : "Not Available"));
        System.out.println("Vehicle Rental Rate (per-day): $" + this.DailyRate);
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
