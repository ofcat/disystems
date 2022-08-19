package com.example.demo.dto;

import java.util.Date;

public class Invoice {

    public int customerID;

    public int stationID;

    public int kwh;

    public Date date;

    public Invoice(){}

    public Invoice(int customerID,int stationID, int kwh, Date date) {
        this.customerID = customerID;
        this.stationID = stationID;
        this.kwh = kwh;
        this.date = date;
    }
}
