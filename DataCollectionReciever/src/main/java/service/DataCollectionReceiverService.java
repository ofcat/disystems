package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataCollectionReceiverService extends BaseService{

    private final String id;

    private List<String> customerList;

    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/chstation?user=admin&password=password";

    public DataCollectionReceiverService(String inDestination, String outDestination, String brokerUrl) {
        super(inDestination, outDestination, brokerUrl);

        this.id = UUID.randomUUID().toString();

        System.out.println("Data Collection Receiver (" + this.id + ") started...");
    }

    @Override
    protected String executeInternal(String input) {

        try (Connection conn = connect()) {
            customerList = new ArrayList<>();

            String sql = "select * from customerList where customer_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);


            input = input.replace("[", "");
            input = input.replace("]", ",");
            input = input.replace(" ", "");

            preparedStatement.setString(1, input.split(",")[0]);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                customerList.add(resultSet.getString("firstname"));
                customerList.add(resultSet.getString("lastname"));
            }
            System.out.println("Data Collection Receiver (" + this.id + ") processed this data: " + customerList);
            return customerList.toString() + input ;

        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong :(";
        }


    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }
}

