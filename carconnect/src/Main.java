
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import DAO.ReservationService;
import DAO.VehicleService;
import models.ReservationModal;
import models.VehicleModal;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static VehicleService vehicleService = new VehicleService();
    private static ReservationService reservationService = new ReservationService();

    public static void main(String[] args) {
        System.out.println("Welcome to CarConnect - Your Premium Car Rental Service");
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    handleVehicleManagement();
                    break;
                case 2:
                    handleReservationManagement();
                    break;
                case 3:
                    running = false;
                    System.out.println("Thank you for using CarConnect. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n=== CarConnect Main Menu ===");
        System.out.println("1. Vehicle Management");
        System.out.println("2. Reservation Management");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleVehicleManagement() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n=== Vehicle Management ===");
            System.out.println("1. View All Vehicles");
            System.out.println("2. View Vehicle by ID");
            System.out.println("3. Add New Vehicle");
            System.out.println("4. Update Vehicle");
            System.out.println("5. Delete Vehicle");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    displayAllVehicles();
                    break;
                case 2:
                    System.out.print("Enter Vehicle ID: ");
                    int vehicleId = getUserChoice();
                    VehicleModal vehicle = vehicleService.getVehicleById(vehicleId);
                    if (vehicle != null) {
                        vehicle.VehicleDisplay();
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;
                case 3:
                    addNewVehicle();
                    break;
                case 4:
                    updateVehicle();
                    break;
                case 5:
                    deleteVehicle();
                    break;
                case 6:
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleReservationManagement() {
        boolean managing = true;
        while (managing) {
            System.out.println("\n=== Reservation Management ===");
            System.out.println("1. View All Reservations");
            System.out.println("2. View Reservation by ID");
            System.out.println("3. Create New Reservation");
            System.out.println("4. Update Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Calculate Reservation Cost");
            System.out.println("7. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    displayAllReservations();
                    break;
                case 2:
                    System.out.print("Enter Reservation ID: ");
                    int reservationId = getUserChoice();
                    ReservationModal reservation = reservationService.getReservationById(reservationId);
                    if (reservation != null) {
                        reservation.ReservationDisplay();
                    } else {
                        System.out.println("Reservation not found.");
                    }
                    break;
                case 3:
                    createNewReservation();
                    break;
                case 4:
                    updateReservation();
                    break;
                case 5:
                    cancelReservation();
                    break;
                case 6:
                    calculateReservationCost();
                    break;
                case 7:
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayAllVehicles() {
        List<VehicleModal> vehicles = vehicleService.getallVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        for (VehicleModal vehicle : vehicles) {
            vehicle.VehicleDisplay();
        }
    }

    private static void addNewVehicle() {
        System.out.println("\nEnter Vehicle Details:");
        scanner.nextLine(); 
        
        System.out.print("Model: ");
        String model = scanner.nextLine();
        
        System.out.print("Make: ");
        String make = scanner.nextLine();
        
        System.out.print("Year: ");
        int year = getUserChoice();
        
        scanner.nextLine(); 
        System.out.print("Color: ");
        String color = scanner.nextLine();
        
        System.out.print("Registration Number: ");
        String regNumber = scanner.nextLine();
        
        System.out.print("Daily Rate: ");
        double dailyRate = scanner.nextDouble();

        VehicleModal newVehicle = new VehicleModal(0, model, make, year, color, regNumber, true, dailyRate);
        VehicleModal result = vehicleService.registerVehicle(newVehicle);
        
        if (result != null) {
            System.out.println("Vehicle registered successfully with ID: " + result.getVehicleID());
        } else {
            System.out.println("Failed to register vehicle. It might already exist.");
        }
    }

    private static void updateVehicle() {
        System.out.print("Enter Vehicle ID to update: ");
        int vehicleId = getUserChoice();
        
        VehicleModal existingVehicle = vehicleService.getVehicleById(vehicleId);
        if (existingVehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        scanner.nextLine(); 
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Model [" + existingVehicle.getModel() + "]: ");
        String model = scanner.nextLine();
        model = model.isEmpty() ? existingVehicle.getModel() : model;
        
        System.out.print("Make [" + existingVehicle.getMake() + "]: ");
        String make = scanner.nextLine();
        make = make.isEmpty() ? existingVehicle.getMake() : make;
        
        System.out.print("Year [" + existingVehicle.getYear() + "]: ");
        String yearStr = scanner.nextLine();
        int year = yearStr.isEmpty() ? existingVehicle.getYear() : Integer.parseInt(yearStr);
        
        System.out.print("Color [" + existingVehicle.getColor() + "]: ");
        String color = scanner.nextLine();
        color = color.isEmpty() ? existingVehicle.getColor() : color;
        
        System.out.print("Registration Number [" + existingVehicle.getRegistrationNumber() + "]: ");
        String regNumber = scanner.nextLine();
        regNumber = regNumber.isEmpty() ? existingVehicle.getRegistrationNumber() : regNumber;
        
        System.out.print("Daily Rate [" + existingVehicle.getDailyRate() + "]: ");
        String rateStr = scanner.nextLine();
        double dailyRate = rateStr.isEmpty() ? existingVehicle.getDailyRate() : Double.parseDouble(rateStr);

        VehicleModal updatedVehicle = new VehicleModal(vehicleId, model, make, year, color, regNumber, existingVehicle.getAvailability(), dailyRate);
        VehicleModal result = vehicleService.updateVehicle(updatedVehicle);
        
        if (result != null) {
            System.out.println("Vehicle updated successfully.");
        } else {
            System.out.println("Failed to update vehicle.");
        }
    }

    private static void deleteVehicle() {
        System.out.print("Enter Vehicle ID to delete: ");
        int vehicleId = getUserChoice();
        if (vehicleService.deleteVehicle(vehicleId)) {
            System.out.println("Vehicle deleted successfully.");
        } else {
            System.out.println("Failed to delete vehicle.");
        }
    }

    private static void displayAllReservations() {
        List<ReservationModal> reservations = reservationService.getallReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        for (ReservationModal reservation : reservations) {
            reservation.ReservationDisplay();
        }
    }

    private static void createNewReservation() {
        System.out.println("\nEnter Reservation Details:");
        
        System.out.print("Customer ID: ");
        int customerId = getUserChoice();
        
        System.out.print("Vehicle ID: ");
        int vehicleId = getUserChoice();
        
        System.out.print("Number of days for reservation: ");
        int days = getUserChoice();
        
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(days);
        
        ReservationModal newReservation = new ReservationModal(0, customerId, vehicleId, 
            Date.valueOf(startDate), Date.valueOf(endDate), "pending");
        
        ReservationModal result = reservationService.registerReservation(newReservation);
        if (result != null) {
            System.out.println("Reservation created successfully with ID: " + result.getReservationID());
        } else {
            System.out.println("Failed to create reservation.");
        }
    }

    private static void updateReservation() {
        System.out.print("Enter Reservation ID to update: ");
        int reservationId = getUserChoice();
        
        ReservationModal existingReservation = reservationService.getReservationById(reservationId);
        if (existingReservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        System.out.print("New status (pending/confirmed/completed/cancelled): ");
        scanner.nextLine(); 
        String status = scanner.nextLine();
        
        existingReservation.setStatus(status);
        ReservationModal result = reservationService.updateReservation(existingReservation);
        
        if (result != null) {
            System.out.println("Reservation updated successfully.");
        } else {
            System.out.println("Failed to update reservation.");
        }
    }

    private static void cancelReservation() {
        System.out.print("Enter Reservation ID to cancel: ");
        int reservationId = getUserChoice();
        if (reservationService.deleteReservation(reservationId)) {
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Failed to cancel reservation.");
        }
    }

    private static void calculateReservationCost() {
        System.out.print("Enter Reservation ID: ");
        int reservationId = getUserChoice();
        reservationService.TotalCost(reservationId);
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}