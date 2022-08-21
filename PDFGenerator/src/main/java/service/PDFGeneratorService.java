package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PDFGeneratorService extends BaseService {

    private final String id;
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chstation?user=admin&password=password";


    public PDFGeneratorService(String inDestination, String outDestination, String brokerUrl) {
        super(inDestination, outDestination, brokerUrl);

        this.id = UUID.randomUUID().toString();

        System.out.println("PDF Generator (" + this.id + ") started...");
    }

    @Override
    protected String executeInternal(String input) {
        //String jobId = UUID.randomUUID().toString();
        List<String> invoices = new ArrayList<>();
        try (Connection conn = connect()) {

            String sql = "SELECT * FROM customerdata where customer_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, Integer.parseInt(input));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                invoices.add(resultSet.getString("customer_id"));
                invoices.add(resultSet.getString("station_id"));
                invoices.add(resultSet.getString("kwh"));
                invoices.add(resultSet.getString("datetime"));

                System.out.println(resultSet.getString("customer_id"));
                System.out.println(resultSet.getString("station_id"));
                System.out.println(resultSet.getString("kwh"));
                System.out.println(resultSet.getString("datetime"));

            }
            return invoices.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }


    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }

}
