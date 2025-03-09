package DAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.ReservationModal;
import services.IReservationServices;
import utils.DBConnection;

import java.util.List;
import java.util.ArrayList;

public class ReservationService implements IReservationServices {
    
    @Override
    public ReservationModal getReservationById(int reservationId) {
        String sql = "SELECT * FROM Reservation WHERE ReservationID=?";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);   
        ) {
            p.setInt(1, reservationId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                int vehicleId = rs.getInt("VehicleID");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                String status = rs.getString("Status");
                return new ReservationModal(reservationId, customerId, vehicleId, startDate, endDate, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ReservationModal getReservationByUserName(String username) {
        String sql = "SELECT r.* FROM Reservation r " +
                    "JOIN Customer c ON r.CustomerID = c.CustomerID " +
                    "WHERE c.Username = ?";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);   
        ) {
            p.setString(1, username);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                int reservationId = rs.getInt("ReservationID");
                int customerId = rs.getInt("CustomerID");
                int vehicleId = rs.getInt("VehicleID");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                String status = rs.getString("Status");
                return new ReservationModal(reservationId, customerId, vehicleId, startDate, endDate, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ReservationModal registerReservation(ReservationModal reservation) {
        String sql = "INSERT INTO Reservation (CustomerID, VehicleID, StartDate, EndDate, Status) VALUES (?, ?, ?, ?, ?)";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            p.setInt(1, reservation.getCustomerID());
            p.setInt(2, reservation.getVehicleID());
            p.setDate(3, reservation.getStartDate());
            p.setDate(4, reservation.getEndDate());
            p.setString(5, reservation.getStatus());
            
            int affectedRows = p.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = p.getGeneratedKeys();
                if (generatedKeys.next()) {
                    reservation.setReservationID(generatedKeys.getInt(1));
                    return reservation;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ReservationModal updateReservation(ReservationModal reservation) {
        String sql = "UPDATE Reservation SET CustomerID=?, VehicleID=?, StartDate=?, EndDate=?, Status=? WHERE ReservationID=?";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
        ) {
            p.setInt(1, reservation.getCustomerID());
            p.setInt(2, reservation.getVehicleID());
            p.setDate(3, reservation.getStartDate());
            p.setDate(4, reservation.getEndDate());
            p.setString(5, reservation.getStatus());
            p.setInt(6, reservation.getReservationID());
            
            int affectedRows = p.executeUpdate();
            if (affectedRows > 0) {
                return reservation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteReservation(int reservationId) {
        String sql = "DELETE FROM Reservation WHERE ReservationID=?";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
        ) {
            p.setInt(1, reservationId);
            int affectedRows = p.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ReservationModal> getallReservations() {
        String sql = "SELECT * FROM Reservation";
        List<ReservationModal> reservations = new ArrayList<>();
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
        ) {
            while (rs.next()) {
                reservations.add(new ReservationModal(
                    rs.getInt("ReservationID"),
                    rs.getInt("CustomerID"),
                    rs.getInt("VehicleID"),
                    rs.getDate("StartDate"),
                    rs.getDate("EndDate"),
                    rs.getString("Status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public void TotalCost(int ReservationID) {
        String sql = "SELECT v.DailyRate, DATEDIFF(r.EndDate, r.StartDate) AS days_diff " +
                    "FROM Reservation r " +
                    "JOIN Vehicle v ON r.VehicleID = v.VehicleID " +
                    "WHERE r.ReservationID=?";

        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql)
        ) {
            p.setInt(1, ReservationID);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    double dailyRate = rs.getDouble("DailyRate");
                    int daysDiff = rs.getInt("days_diff");
                    double totalCost = dailyRate * daysDiff;
                    System.out.println("Total Cost for Reservation #" + ReservationID + ": $" + String.format("%.2f", totalCost));
                } else {
                    System.out.println("No reservation found with ID: " + ReservationID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to calculate total cost of a reservation
    public double calculateTotalCost(int reservationId) {
        String sql = "SELECT v.DailyRate, DATEDIFF(r.EndDate, r.StartDate) AS days_diff " +
                    "FROM Reservation r " +
                    "JOIN Vehicle v ON r.VehicleID = v.VehicleID " +
                    "WHERE r.ReservationID=?";

        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
        ) {
            p.setInt(1, reservationId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                double dailyRate = rs.getDouble("DailyRate");
                int daysDiff = rs.getInt("days_diff");
                return dailyRate * daysDiff;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
