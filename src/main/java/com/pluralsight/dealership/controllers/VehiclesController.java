package com.pluralsight.dealership.controllers;

import com.pluralsight.dealership.dao.VehicleDAO;
import com.pluralsight.dealership.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehiclesController {

    @Autowired
    private VehicleDAO vehicleDao;

    @GetMapping
    public List<Vehicle> getVehicles() throws SQLException {
        return vehicleDao.getVehicles();
    }

    @PostMapping
    public void addVehicle(@RequestBody Vehicle vehicle) throws SQLException {
        vehicleDao.addVehicle(vehicle);
    }

    @PutMapping("/{vin}")
    public void updateVehicle(@PathVariable String vin, @RequestBody Vehicle updatedVehicle) throws SQLException {
        vehicleDao.updateVehicle(vin, updatedVehicle);
    }

    @DeleteMapping("/{vin}")
    public void deleteVehicle(@PathVariable String vin) throws SQLException {
        vehicleDao.removeVehicle(vin);
    }
}
