package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.model.LeaseContract;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeaseDAO {
    private final DataSource dataSource;

    public LeaseDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<LeaseContract> getLeaseContracts() throws SQLException {
        List<LeaseContract> leaseContracts = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                leaseContracts.add(new LeaseContract(
                        rs.getString("contract_date"),
                        rs.getString("customer_name"),
                        rs.getString("vin"),
                        rs.getDouble("original_price"),
                        rs.getDouble("total_price"),
                        rs.getDouble("monthly_payment")
                ));
            }
        }
        return leaseContracts;
    }

    public void saveLeaseContract(LeaseContract contract) throws SQLException {
        String query = "INSERT INTO lease_contracts (contract_date, customer_name, vin, original_price, total_price, monthly_payment) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, contract.getContractDate());
            stmt.setString(2, contract.getCustomerName());
            stmt.setString(3, contract.getVin());
            stmt.setDouble(4, contract.getOriginalPrice());
            stmt.setDouble(5, contract.getTotalPrice());
            stmt.setDouble(6, contract.getMonthlyPayment());
            stmt.executeUpdate();
        }
    }
}
