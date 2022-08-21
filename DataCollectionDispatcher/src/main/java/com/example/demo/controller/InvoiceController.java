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

@RestController
public class InvoiceController {

    private final static String BROKER_URL = "tcp://localhost:61616";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chstation?user=admin&password=password";

    @PostMapping("/invoices/{customerID}")
    public String createRequest( @PathVariable String customerID) {

        //:TODO add id validation (only integers allowed)
        Producer.send(customerID, "StationDataGathering", BROKER_URL);

        return customerID;
    }

    //todo: figure out how to set where the file is saved
    @GetMapping(value ="/invoices/{id}")
    public String downloadPDF(@PathVariable String id) {
        File directoryPath = new File("/Users/vasilii/IdeaProjects/ChargingStation/invoices");
        // /Users/vasilii/IdeaProjects/ChargingStation/PDFGenerator/invoices
        String[] contents = directoryPath.list();
        String name = "";
        for (String content : contents) {
            if (content.contains(id)) {
                name = content;
            }
        }
        return name;

    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }


}
