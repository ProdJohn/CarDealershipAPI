package com.pluralsight.dealership.model;

public class LeaseContract {
    private String contractDate;
    private String customerName;
    private String vin;
    private double originalPrice;
    private double totalPrice;
    private double monthlyPayment;

    public LeaseContract(String contractDate, String customerName, String vin, double originalPrice, double totalPrice, double monthlyPayment) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.vin = vin;
        this.originalPrice = originalPrice;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;

    }
    public String getContractDate() {
        return contractDate;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getVin() {
        return vin;
    }
    public double getOriginalPrice() {
        return originalPrice;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public double getMonthlyPayment() {
        return monthlyPayment;
    }
}
