package service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TestConsumeService extends BaseService{

    private final String id;

    private List<String> customerList;

    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chstation?user=admin&password=password";

    public TestConsumeService(String inDestination, String outDestination, String brokerUrl) {
        super(inDestination, outDestination, brokerUrl);

        this.id = UUID.randomUUID().toString();

        System.out.println("Data Dispatcher (" + this.id + ") started...");
    }

    @Override
    protected String executeInternal(String input) {
        String jobId = UUID.randomUUID().toString();

        try (Connection conn = connect()) {
            customerList = new ArrayList<>();

            String sql = "select * from customerList where cusotmer_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            input = input.replace("[", "");
            input = input.replace("]", ",");
            input = input.replace(" ", "");

            preparedStatement.setString(1, input.split(",")[0]);


            //preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                customerList.add(resultSet.getString("firstname"));
                customerList.add(resultSet.getString("lastname"));
                System.out.println(resultSet.getString("firstname"));
                System.out.println(resultSet.getString("lastname"));
            }
            System.out.println("ok");
            return input + customerList.toString();

        } catch (SQLException e) {
            return "error";
        }


    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }
}

