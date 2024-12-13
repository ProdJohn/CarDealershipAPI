package com.pluralsight.dealership;

import com.pluralsight.dealership.dao.VehicleDAO;
import com.pluralsight.dealership.dao.SalesDAO;
import com.pluralsight.dealership.dao.LeaseDAO;
import com.pluralsight.dealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.Scanner;

public class DealershipApp {

    @Autowired
    private VehicleDAO vehicleDao;

    @Autowired
    private SalesDAO salesDao;

    @Autowired
    private LeaseDAO leaseDao;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Dealership App ===");
            System.out.println("1. View Vehicles");
            System.out.println("2. View Sales Contracts");
            System.out.println("3. View Lease Contracts");
            System.out.println("4. Add a Vehicle");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> viewVehicles();
                    case 2 -> viewSalesContracts();
                    case 3 -> viewLeaseContracts();
                    case 4 -> addVehicle(scanner);
                    case 5 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void viewVehicles() throws SQLException {
        for (Vehicle vehicle : vehicleDao.getVehicles()) {
            System.out.println(vehicle);
        }
    }

    private void viewSalesContracts() throws SQLException {
        for (var contract : salesDao.getSalesContracts()) {
            System.out.println(contract);
        }
    }

    private void viewLeaseContracts() throws SQLException {
        for (var contract : leaseDao.getLeaseContracts()) {
            System.out.println(contract);
        }
    }

    private void addVehicle(Scanner scanner) throws SQLException {
        System.out.print("VIN: ");
        String vin = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();

        vehicleDao.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
        System.out.println("Vehicle added successfully!");
    }
}
