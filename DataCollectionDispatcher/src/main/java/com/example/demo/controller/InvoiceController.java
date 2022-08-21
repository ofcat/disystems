package com.example.demo.controller;

import com.example.demo.dto.Invoice;
import com.example.demo.queue.communication.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {

    private final static String BROKER_URL = "tcp://localhost:61616"; //6616
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chstation?user=admin&password=password";

    @PostMapping("/invoices/{customerID}")
    public String createRequest( @PathVariable String customerID) {

        //:TODO add id validation (only integers allowed)
        Producer.send(customerID, "StationDataGathering", BROKER_URL);

        return customerID;
    }

    @GetMapping(value ="/invoices/{id}")
    public String downloadPDF(@PathVariable String id) {
        File directoryPath = new File("/Users/vasilii/IdeaProjects/ChargingStation/PDFGenerator/invoices");
        // /Users/vasilii/IdeaProjects/ChargingStation/PDFGenerator/invoices
        String[] contents = directoryPath.list();
        String name = "";
        for (String content : contents) {
            if (content.contains(id)) {
                //list.add(content);
                name = content;
            }
        }
        return name;

    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }


}
