package com.example.demo.controller;

import com.example.demo.dto.Invoice;
import com.example.demo.queue.communication.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class InvoiceController {

    private final static String BROKER_URL = "tcp://localhost:61616"; //6616
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chstation?user=admin&password=password";

    @PostMapping("/invoices/{customerID}")
    public String createRequest(@PathVariable String type, @PathVariable String customerID) {

        //:TODO add id validation (only integers allowed)
        Producer.send(customerID, type, BROKER_URL);

        return customerID;
    }

    @GetMapping(value ="/invoices/{id}",produces = "application/json")
    public List<Invoice> downloadPDF(@PathVariable int id) {

        //:todo change method to return pdf
        List<Invoice> invoices = new ArrayList<>();

        try (Connection conn = connect()) {

            String sql = "SELECT * FROM customerdata where customer_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Invoice invoice = new Invoice();

                invoice.kwh = resultSet.getInt("kwh");
                invoice.customerID = resultSet.getInt("customer_id");
                invoice.stationID = resultSet.getInt("station_id");
                invoice.date = resultSet.getDate("datetime");

                invoices.add(invoice);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return invoices;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }


}
