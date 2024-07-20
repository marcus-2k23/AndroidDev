package com.example.assignment1.models;

public class Order {
    private final String customerName;
    private final String emailAddress;
    private final String phoneNumber;
    private final String beverageType;
    private final boolean addMilk;
    private final boolean addSugar;
    private final String beverageSize;
    private final String flavoring;
    private final String city;
    private final String store;
    private final String saleDate;

    public Order(String customerName, String emailAddress, String phoneNumber, String beverageType, boolean addMilk, boolean addSugar, String beverageSize, String flavoring, String city, String store, String saleDate) {
        this.customerName = customerName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.beverageType = beverageType;
        this.addMilk = addMilk;
        this.addSugar = addSugar;
        this.beverageSize = beverageSize;
        this.flavoring = flavoring;
        this.city = city;
        this.store = store;
        this.saleDate = saleDate;
    }

    public String createOrderReceipt() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Customer Name: ").append(customerName).append("\n");
        receipt.append("Phone Number: ").append(phoneNumber).append("\n");
        receipt.append("Email Address: ").append(emailAddress).append("\n");
        receipt.append("Beverage Type: ").append(beverageType).append("\n");
        receipt.append("Add Milk: ").append(addMilk ? "Yes" : "No").append("\n");
        receipt.append("Add Sugar: ").append(addSugar ? "Yes" : "No").append("\n");
        receipt.append("Beverage Size: ").append(beverageSize).append("\n");
        receipt.append("Flavoring: ").append(flavoring).append("\n");
        receipt.append("City: ").append(city).append("\n");
        receipt.append("Store: ").append(store).append("\n");
        receipt.append("Sale Date: ").append(saleDate).append("\n");
        receipt.append("Price: $").append(calculateTotalPrice()).append("\n");
        return receipt.toString();
    }

    private double calculateTotalPrice() {
        double price = 0.0;

        // Calculate price based on beverage type and size
        if ("coffee".equalsIgnoreCase(beverageType)) {
            if ("small".equalsIgnoreCase(beverageSize)) {
                price = 1.75;
            } else if ("medium".equalsIgnoreCase(beverageSize)) {
                price = 2.75;
            } else if ("large".equalsIgnoreCase(beverageSize)) {
                price = 3.75;
            }
        } else if ("tea".equalsIgnoreCase(beverageType)) {
            if ("small".equalsIgnoreCase(beverageSize)) {
                price = 1.5;
            } else if ("medium".equalsIgnoreCase(beverageSize)) {
                price = 2.5;
            } else if ("large".equalsIgnoreCase(beverageSize)) {
                price = 3.25;
            }
        }

        // Add the cost of milk and sugar
        if (addMilk) {
            price += 1.25;
        }
        if (addSugar) {
            price += 1.0;
        }

        // Add flavoring cost
        if ("Lemon".equalsIgnoreCase(flavoring)) {
            price += 0.25;
        } else if ("Ginger".equalsIgnoreCase(flavoring)) {
            price += 0.75;
        } else if ("Pumpkin Spice".equalsIgnoreCase(flavoring)) {
            price += 0.5;
        } else if ("Chocolate".equalsIgnoreCase(flavoring)) {
            price += 0.75;
        }

        // Apply tax rate
        price *= (1 + 0.13);

        return price;
    }
}
