package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.model.SalesContract;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalesDAO {
    private final DataSource dataSource;

    public SalesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<SalesContract> getSalesContracts() throws SQLException {
        List<SalesContract> salesContracts = new ArrayList<>();
        String query = "SELECT * FROM sales_contracts";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                salesContracts.add(new SalesContract(
                        rs.getString("sale_date"),
                        rs.getString("buyer_name"),
                        rs.getString("VIN"),
                        rs.getDouble("sales_price")

                ));
            }
        }
        return salesContracts;
    }

    public void saveSalesContract(SalesContract contract) throws SQLException {
        String query = "INSERT INTO sales_contracts (sale_date, buyer_name, vin, sales_price, total_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, contract.getSaleDate());
            stmt.setString(2, contract.getBuyerName());
            stmt.setString(3, contract.getVin());
            stmt.setDouble(4, contract.getSalesPrice());
            stmt.executeUpdate();
        }
    }
}
