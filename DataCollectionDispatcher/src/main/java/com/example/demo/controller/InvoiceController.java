package com.example.demo.controller;


import com.example.demo.queue.communication.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class InvoiceController {

    //using Set to ensure that there are no duplicates in customer ids
    //https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java
    private final static Set<String> CUSTOMERS = Set.of("5555", "455", "8000");
    private final static String BROKER_URL = "tcp://localhost:61616";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chstation?user=admin&password=password";

    @PostMapping("/invoices/{customerID}")
    public String createRequest(@PathVariable String customerID) {

        if (CUSTOMERS.contains(customerID)) {
            Producer.send(customerID, "StationDataGathering", BROKER_URL);
        } else {
            downloadPDF("not a customer");
        }
        return customerID;
    }

    @GetMapping(value = "/invoices/{customerID}")
    public List<String> downloadPDF(@PathVariable String customerID) {
        List<String> recentInvoices = new ArrayList<>();

        if (CUSTOMERS.contains(customerID)) {

            File directoryPath = new File("/Users/vasilii/IdeaProjects/ChargingStation/");
            String[] filesList = directoryPath.list();

            for (String file : filesList) {
                if (file.contains(customerID)) {
                    recentInvoices.add(file);
                }
            }

        } else {
            recentInvoices.add("404 Not Found");
        }
        return recentInvoices;


    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }


}
