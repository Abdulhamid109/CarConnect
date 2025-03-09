package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.VehicleModal;
import services.IVehicleServices;
import utils.DBConnection;
import java.util.List;
import java.util.ArrayList;

public class VehicleService implements IVehicleServices {
    
    @Override
    public VehicleModal getVehicleById(int vehicleId) {
        String sql = "SELECT * FROM Vehicle WHERE VehicleID=?";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
        ) {
            p.setInt(1, vehicleId);
            try (ResultSet rs = p.executeQuery()) { 
                if (rs.next()) { 
                    return new VehicleModal(
                        rs.getInt("VehicleID"),
                        rs.getString("Model"),
                        rs.getString("Make"),
                        rs.getInt("Year"),
                        rs.getString("Color"),
                        rs.getString("RegistrationNumber"),
                        rs.getBoolean("Availability"),
                        rs.getDouble("DailyRate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VehicleModal getVehicleByRegistrationNumber(String registrationNumber) {
        String sql = "SELECT * FROM Vehicle WHERE RegistrationNumber = ?";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
        ) {
            p.setString(1, registrationNumber);
            try (ResultSet rs = p.executeQuery()) {
                if (rs.next()) {
                    return new VehicleModal(
                        rs.getInt("VehicleID"),
                        rs.getString("Model"),
                        rs.getString("Make"),
                        rs.getInt("Year"),
                        rs.getString("Color"),
                        rs.getString("RegistrationNumber"),
                        rs.getBoolean("Availability"),
                        rs.getDouble("DailyRate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VehicleModal registerVehicle(VehicleModal vehicle) {
        String sql = "INSERT INTO Vehicle (Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        
        if (getVehicleByRegistrationNumber(vehicle.getRegistrationNumber()) != null) {
            return null;
        }

        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            p.setString(1, vehicle.getModel());
            p.setString(2, vehicle.getMake());
            p.setInt(3, vehicle.getYear());
            p.setString(4, vehicle.getColor());
            p.setString(5, vehicle.getRegistrationNumber());
            p.setBoolean(6, vehicle.getAvailability());
            p.setDouble(7, vehicle.getDailyRate());

            int affectedRows = p.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = p.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        vehicle.setVehicleID(generatedKeys.getInt(1));
                        return vehicle;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public VehicleModal updateVehicle(VehicleModal vehicle) {
        String sql = "UPDATE Vehicle SET Model=?, Make=?, Year=?, Color=?, RegistrationNumber=?, Availability=?, DailyRate=? WHERE VehicleID=?";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
        ) {
            p.setString(1, vehicle.getModel());
            p.setString(2, vehicle.getMake());
            p.setInt(3, vehicle.getYear());
            p.setString(4, vehicle.getColor());
            p.setString(5, vehicle.getRegistrationNumber());
            p.setBoolean(6, vehicle.getAvailability());
            p.setDouble(7, vehicle.getDailyRate());
            p.setInt(8, vehicle.getVehicleID());

            int affectedRows = p.executeUpdate();
            if (affectedRows > 0) {
                return vehicle;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        String sql = "DELETE FROM Vehicle WHERE VehicleID=?";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
        ) {
            p.setInt(1, vehicleId);
            int affectedRows = p.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<VehicleModal> getallVehicles() {
        List<VehicleModal> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle";
        try (
            Connection conn = DBConnection.getconnection();
            PreparedStatement p = conn.prepareStatement(sql);
            ResultSet rs = p.executeQuery()
        ) {
            while (rs.next()) {
                vehicles.add(new VehicleModal(
                    rs.getInt("VehicleID"),
                    rs.getString("Model"),
                    rs.getString("Make"),
                    rs.getInt("Year"),
                    rs.getString("Color"),
                    rs.getString("RegistrationNumber"),
                    rs.getBoolean("Availability"),
                    rs.getDouble("DailyRate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}
