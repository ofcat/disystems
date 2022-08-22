package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class HelloController {

    private static final String API = "http://localhost:8080";
    @FXML
    private TextField customerID;
    private String ID;
    @FXML
    private ListView<String> invoiceList;

    @FXML
    private void generateInvoice() throws URISyntaxException, IOException, InterruptedException { // change to generateInvoice
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API + "/invoices/" + customerID.getText()))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        ID = String.valueOf(customerID.getText());

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        //uncomment to update pdf list on "Generate Invoice" button press
        //updateInvoicesList();
        customerID.setText("");
    }
    public void updateInvoicesList() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/invoices/" +  ID))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());


        ObservableList<String> invoices = FXCollections.observableArrayList();

        String responseModified = response.body().replace("[", "")
                .replace("]", "")
                .replace("\"", "");

        String[] pdfs = responseModified.split(",");

        invoices.addAll(Arrays.asList(pdfs));

        invoiceList.setItems(invoices);

    }

    public void openPDF() {
        try {
            File myFile = new File("/Users/vasilii/IdeaProjects/ChargingStation/"+invoiceList.getSelectionModel().getSelectedItem());
            Desktop.getDesktop().open(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}