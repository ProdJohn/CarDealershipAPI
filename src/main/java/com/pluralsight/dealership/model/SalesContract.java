package com.pluralsight.dealership.model;

public class SalesContract {
    private String saleDate;
    private String buyerName;
    private String vin;
    private double salesPrice;
    private double totalPrice;

    public SalesContract(String contractDate, String customerName, String vin, double salesPrice) {
        this.saleDate = contractDate;
        this.buyerName = customerName;
        this.vin = vin;
        this.salesPrice = salesPrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getVin() {
        return vin;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public String toString () {
        return "SalesContract{" +
                "contractDate='" + saleDate + '\'' +
                ", customerName='" + buyerName + '\'' +
                ", vin='" + vin + '\'' +
                ", salesPrice=" + salesPrice +
                '}';
    }
}