package service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
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

        input = input.replace("[", "");
        input = input.replace("]", ",");

        String[] invoiceData = input.split(",");

        String firstName = invoiceData[0];
        String lastName = invoiceData[1];
        String customerID = invoiceData[2];
        String stationID = invoiceData[3];
        float kwh = Float.parseFloat((invoiceData[4]));
        String datetime = invoiceData[5];

        float amountToPay = (float) (kwh * 4.99);


        Document document = new Document();

        String date = String.valueOf(new Date());
        PdfWriter.getInstance(document, new FileOutputStream("CustomerID[" + customerID + "]" + date + ".pdf"));

        document.open();


        Paragraph loremIpsumHeader = new Paragraph("Customer Invoice - " + date);
        document.add(loremIpsumHeader);

        Paragraph lineBreak = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------");
        document.add(lineBreak);

        //http://tutorials.jenkov.com/java-itext/table.html
        PdfPTable table = new PdfPTable(7);

        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(10f);

        float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 2f, 1f};
        table.setWidths(columnWidths);

        PdfPCell header1 = new PdfPCell(new Paragraph("First Name"));
        PdfPCell header2 = new PdfPCell(new Paragraph("Last Name"));
        PdfPCell header3 = new PdfPCell(new Paragraph("Customer ID"));
        PdfPCell header4 = new PdfPCell(new Paragraph("Station ID"));
        PdfPCell header5 = new PdfPCell(new Paragraph("KWH Used"));
        PdfPCell header6 = new PdfPCell(new Paragraph("Date and Time"));
        PdfPCell header7 = new PdfPCell(new Paragraph("Amount to Pay"));

        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);
        table.addCell(header5);
        table.addCell(header6);
        table.addCell(header7);

        PdfPCell cell1 = new PdfPCell(new Paragraph(firstName));
        PdfPCell cell2 = new PdfPCell(new Paragraph(lastName));
        PdfPCell cell3 = new PdfPCell(new Paragraph(customerID));
        PdfPCell cell4 = new PdfPCell(new Paragraph(stationID));
        PdfPCell cell5 = new PdfPCell(new Paragraph(String.valueOf(kwh)));
        PdfPCell cell6 = new PdfPCell(new Paragraph(datetime));
        PdfPCell cell7 = new PdfPCell(new Paragraph(String.valueOf(amountToPay)));


        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);

        document.add(table);

        document.add(lineBreak);


        document.add(new Paragraph("Please pay :)"));

        document.close();

        System.out.println("PDF Generator (" + this.id + ") used this data to generate a pdf: " + Arrays.toString(invoiceData));
        return input;


    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }

}
