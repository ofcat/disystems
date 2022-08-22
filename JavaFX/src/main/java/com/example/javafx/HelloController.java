package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloController {

    private static final String API = "http://localhost:8080";
    @FXML
    private TextField customerID; // change to customerID
    @FXML
    private TextField output; // change

    @FXML
    private ListView<String> invoiceList;

    @FXML
    private void generateInvoice() throws URISyntaxException, IOException, InterruptedException { // change to generateInvoice
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API + "/invoices/" + customerID.getText()))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        //todo: check if this can be removed
        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        checkfornewInvoice();
        customerID.setText("");
    }
    String x="";
    public String checkfornewInvoice() throws URISyntaxException, IOException, InterruptedException {
// todo: make a separate function
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/invoices/" +  customerID.getText()))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


// todo: check if this can be removed
        output.setText(response.body());
        System.out.println(response.body());
        x= response.body();
        return response.body();
    }

    public void downloadPdf(MouseEvent mouseEvent) {
// todo: make it a button which opens a directory with saved pdfs

        try {
            File myFile = new File("/Users/vasilii/IdeaProjects/ChargingStation/"+x);
            Desktop.getDesktop().open(myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @FXML
//    private void downloadInvoice() throws URISyntaxException, IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI(API + "/invoices/" + customerID.getText()))
//                .GET()
//                .build();
//
//        HttpResponse<String> response = HttpClient.newBuilder()
//                .build()
//                .send(request, HttpResponse.BodyHandlers.ofString());
//
//        // System.out.println(response.body());
//        JSONArray jsonArray = new JSONArray(response.body());
//
//        ObservableList<String> invoices = FXCollections.observableArrayList();
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            invoices.add(jsonObject.toString());
//        }
//
//        invoiceList.setItems(invoices);
//    }

}