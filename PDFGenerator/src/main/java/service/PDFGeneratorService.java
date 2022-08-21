package service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    protected String executeInternal(String input) throws DocumentException, IOException, URISyntaxException {

        String jobId=UUID.randomUUID().toString();
        // System.out.println("Executes"+input+" "+jobId);
        System.out.println(input);
        input = input.replace("[", "");
        input = input.replace("]", ",");
        input = input.replace(" ", "");

        System.out.println(input);
        String id= input.split(",")[0];
        String stationID= input.split(",")[1];

//        String vorname= input.split(",")[2];
//        String nachname= input.split(",")[3];
//        String adresse= input.split(",")[4];

       // Path path = Paths.get(ClassLoader.getSystemResource("logo.png").toURI());
        Document document = new Document();

//        String date= LocalTime.now().toString();
//        date=date.replace(":","-");
        System.out.println(input);
        PdfWriter.getInstance(document, new FileOutputStream("date"+"["+id+"]"+".pdf"));

        document.open();
//        Image img = Image.getInstance(path.toAbsolutePath().toString());
//        document.add(img);



        // header
        Paragraph p;
        p = new Paragraph(LocalDate.now().toString());
        p.setAlignment(Element.ALIGN_RIGHT);


        document.add(p);
        p = new Paragraph(id);
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);
        p = new Paragraph("INVOICE");
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        p = new Paragraph(id);
        p.setAlignment(Element.ALIGN_LEFT);
        document.add(p);
        p = new Paragraph(stationID);
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);


//        Double newinput= Double.parseDouble(verbrauch);
//
//        double preis= newinput*22.15;
//
//        p = new Paragraph("Preis: " +preis + " €" );
//        p.setAlignment(Element.ALIGN_RIGHT);
//        document.add(p);


        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);



        document.close();

        System.out.println(input);
        return input;








        //String jobId = UUID.randomUUID().toString();
//        List<String> invoices = new ArrayList<>();
//        try (Connection conn = connect()) {
//
//            String sql = "SELECT * FROM customerdata where customer_id = ?";
//
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//
//            preparedStatement.setInt(1, Integer.parseInt(input));
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//
//                invoices.add(resultSet.getString("customer_id"));
//                invoices.add(resultSet.getString("station_id"));
//                invoices.add(resultSet.getString("kwh"));
//                invoices.add(resultSet.getString("datetime"));
//
//                System.out.println(resultSet.getString("customer_id"));
//                System.out.println(resultSet.getString("station_id"));
//                System.out.println(resultSet.getString("kwh"));
//                System.out.println(resultSet.getString("datetime"));
//
//            }
//            return invoices.toString();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return "error";
//        }


    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }

}
