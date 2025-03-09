package DAO;

import models.CustomerModal;
import services.ICustomerService;
import utils.DBConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerService implements ICustomerService {

    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

    @Override
    public CustomerModal getCustomerById(int customerId) {
        CustomerModal customer = null;
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new CustomerModal();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setAddress(rs.getString("Address"));
                customer.setUserName(rs.getString("UserName"));
                customer.setPassword(rs.getString("Password"));
                Timestamp ts = rs.getTimestamp("RegistrationDate");
                if (ts != null) {
                    customer.setRegistrationDate(ts.toLocalDateTime());
                }
            }
        }   catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving customer by ID: " + customerId, e);
        }
        return customer;
    }

    @Override
    public CustomerModal getCustomerByUserName(String username) {
        CustomerModal customer = null;
        String sql = "SELECT * FROM Customer WHERE UserName = ?";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new CustomerModal();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setEmail(rs.getString("Email"));
                customer.setPhoneNumber(rs.getString("PhoneNumber"));
                customer.setAddress(rs.getString("Address"));
                customer.setUserName(rs.getString("UserName"));
                customer.setPassword(rs.getString("Password"));
                Timestamp ts = rs.getTimestamp("RegistrationDate");
                if (ts != null) {
                    customer.setRegistrationDate(ts.toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving customer by username: " + username, e);
        }
        return customer;
    }
    @Override
    public CustomerModal registerCustomer(CustomerModal customer) {
        String sql = "INSERT INTO Customer (FirstName, LastName, Email, PhoneNumber, Address, UserName, Password, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getUserName());
            stmt.setString(7, customer.getPassword());
            stmt.setTimestamp(8, Timestamp.valueOf(customer.getRegistrationDate()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Registering customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setCustomerID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Registering customer failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error registering customer: " + customer.getUserName(), e);
        }
        return customer;
    }
    @Override
    public CustomerModal updateCustomer(CustomerModal customer) {
        String sql = "UPDATE Customer SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ?, UserName = ?, Password = ? WHERE CustomerID = ?";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getAddress());
            stmt.setString(6, customer.getUserName());
            stmt.setString(7, customer.getPassword());
            stmt.setInt(8, customer.getCustomerID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                logger.log(Level.WARNING, "Update failed: No customer found with ID " + customer.getCustomerID());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating customer: " + customer.getCustomerID(), e);
        }
        return customer;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customer WHERE CustomerID = ?";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return true;
            } else {
                logger.log(Level.WARNING, "Delete failed: No customer found with ID " + customerId);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting customer with ID " + customerId, e);
        }
        return false;
    }
}