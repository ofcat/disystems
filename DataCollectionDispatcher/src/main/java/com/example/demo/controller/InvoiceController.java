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
import java.util.Set;

@RestController
public class InvoiceController {

    //using Set to ensure that there are no duplicates in customer ids
    //https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java
    private final static Set<String> CUSTOMERS = Set.of("5555", "455", "8000");
    private final static String BROKER_URL = "tcp://localhost:61616";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chstation?user=admin&password=password";

    @PostMapping("/invoices/{customerID}")
    public String createRequest( @PathVariable String customerID) {


        Producer.send(customerID, "StationDataGathering", BROKER_URL);

        return customerID;
    }

    //todo: figure out how to set where the file is saved
    @GetMapping(value ="/invoices/{id}")
    public String downloadPDF(@PathVariable String id) {

        if (CUSTOMERS.contains(id)){

            File directoryPath = new File("/Users/vasilii/IdeaProjects/ChargingStation/");
            String[] contents = directoryPath.list();
            String fileName = "";

            for (String content : contents) {
                if (content.contains(id)) {
                    fileName = content;
                }
            }

            return fileName;
        } else{
            return "404 Not Found";
        }


    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }




}
