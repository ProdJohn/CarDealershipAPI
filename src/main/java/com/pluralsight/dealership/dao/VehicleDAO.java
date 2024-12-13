package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.model.Vehicle;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleDAO {
    private final DataSource dataSource;

    public VehicleDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> getVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getString("vin"),
                        rs.getInt("year"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getString("vehicleType"),
                        rs.getString("color"),
                        rs.getInt("odometer"),
                        rs.getDouble("price")
                ));
            }
        }
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicles (vin, year, make, model, vehicleType, color, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getVehicleType());
            stmt.setString(6, vehicle.getColor());
            stmt.setInt(7, vehicle.getOdometer());
            stmt.setDouble(8, vehicle.getPrice());
            stmt.executeUpdate();
        }
    }

    public void updateVehicle(String vin, Vehicle updatedVehicle) throws SQLException {
        String query = "UPDATE vehicles SET year = ?, make = ?, model = ?, vehicleType = ?, color = ?, odometer = ?, price = ? WHERE vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, updatedVehicle.getYear());
            stmt.setString(2, updatedVehicle.getMake());
            stmt.setString(3, updatedVehicle.getModel());
            stmt.setString(4, updatedVehicle.getVehicleType());
            stmt.setString(5, updatedVehicle.getColor());
            stmt.setInt(6, updatedVehicle.getOdometer());
            stmt.setDouble(7, updatedVehicle.getPrice());
            stmt.setString(8, vin);
            stmt.executeUpdate();
        }
    }

    public void removeVehicle(String vin) throws SQLException {
        String query = "DELETE FROM vehicles WHERE vin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vin);
            stmt.executeUpdate();
        }
    }
}
