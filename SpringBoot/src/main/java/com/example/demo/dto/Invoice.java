package com.example.demo.dto;

public class Invoice {

    public String type;
    public String status;
    public String customerID;

    public Invoice(){}

    public Invoice(String type, String status, String customerID) {
        this.type = type;
        this.status = "send...";
        this.customerID = customerID;
    }
}
