import service.PDFGeneratorService;

public class PDFGeneratorMain {
    private final static String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) {
        PDFGeneratorService PDFGenerator = new PDFGeneratorService("PDFGeneration", "PDFDONE", BROKER_URL);
        PDFGenerator.run();
    }
}
