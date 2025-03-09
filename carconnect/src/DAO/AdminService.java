package DAO;



import models.AdminModal;
import services.IAdminServices;
import utils.DBConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminService implements IAdminServices {

    private static final Logger logger = Logger.getLogger(AdminService.class.getName());

    @Override
    public AdminModal getAdminById(int adminId) {
        AdminModal admin = null;
        String sql = "SELECT * FROM Admin WHERE AdminID = ?";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                admin = new AdminModal();
                admin.setAdminID(rs.getInt("AdminID"));
                admin.setFirstName(rs.getString("FirstName"));
                admin.setLastName(rs.getString("LastName"));
                admin.setEmail(rs.getString("Email"));
                admin.setPhoneNumber(rs.getString("PhoneNumber"));
                admin.setUserName(rs.getString("UserName"));
                admin.setPassword(rs.getString("Password"));
                admin.setRole(rs.getString("Role"));
                Timestamp ts = rs.getTimestamp("JoinDate");
                if (ts != null) {
                    admin.setJoinDate(ts.toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving admin by ID: " + adminId, e);
        }
        return admin;
    }

    @Override
    public AdminModal getAdminByUsername(String username) {
        AdminModal admin = null;
        String sql = "SELECT * FROM Admin WHERE UserName = ?";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                admin = new AdminModal();
                admin.setAdminID(rs.getInt("AdminID"));
                admin.setFirstName(rs.getString("FirstName"));
                admin.setLastName(rs.getString("LastName"));
                admin.setEmail(rs.getString("Email"));
                admin.setPhoneNumber(rs.getString("PhoneNumber"));
                admin.setUserName(rs.getString("UserName"));
                admin.setPassword(rs.getString("Password"));
                admin.setRole(rs.getString("Role"));
                Timestamp ts = rs.getTimestamp("JoinDate");
                if (ts != null) {
                    admin.setJoinDate(ts.toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving admin by username: " + username, e);
        }
        return admin;
    }

    @Override
    public AdminModal registerAdmin(AdminModal admin) {
        String sql = "INSERT INTO Admin (FirstName, LastName, Email, PhoneNumber, UserName, Password, Role, JoinDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPhoneNumber());
            stmt.setString(5, admin.getUserName());
            stmt.setString(6, admin.getPassword());
            stmt.setString(7, admin.getRole());
            stmt.setTimestamp(8, Timestamp.valueOf(admin.getJoinDate()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Registering admin failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    admin.setAdminID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Registering admin failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error registering admin: " + admin.getUserName(), e);
        }
        return admin;
    }

    @Override
    public AdminModal updateAdmin(AdminModal admin) {
        String sql = "UPDATE Admin SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, UserName = ?, Password = ?, Role = ? WHERE AdminID = ?";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, admin.getFirstName());
            stmt.setString(2, admin.getLastName());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPhoneNumber());
            stmt.setString(5, admin.getUserName());
            stmt.setString(6, admin.getPassword());
            stmt.setString(7, admin.getRole());
            stmt.setInt(8, admin.getAdminID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                logger.log(Level.WARNING, "Update failed: No admin found with ID " + admin.getAdminID());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating admin with ID " + admin.getAdminID(), e);
        }
        return admin;
    }

    @Override
    public boolean deleteAdmin(int adminId) {
        String sql = "DELETE FROM Admin WHERE AdminID = ?";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adminId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return true;
            } else {
                logger.log(Level.WARNING, "Delete failed: No admin found with ID " + adminId);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting admin with ID " + adminId, e);
        }
        return false;
    }
}
