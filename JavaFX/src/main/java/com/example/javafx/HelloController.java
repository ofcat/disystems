package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloController {

    private static final String API = "http://localhost:8080";
    @FXML
    private Label welcomeText;

    @FXML
    private TextField customerID; // change to customerID

    @FXML
    private ListView<String> invoiceList;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Invoice Controller");
    }


    @FXML
    private void generateInvoice() throws URISyntaxException, IOException, InterruptedException { // change to generateInvoice
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API + "/invoices/pending/" + customerID.getText()))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        customerID.setText("");
    }

    @FXML
    private void downloadInvoice() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API + "/invoices/" + customerID.getText()))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        // System.out.println(response.body());
        JSONArray jsonArray = new JSONArray(response.body());

        ObservableList<String> invoices = FXCollections.observableArrayList();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            invoices.add(jsonObject.toString());
        }

        invoiceList.setItems(invoices);
    }

}