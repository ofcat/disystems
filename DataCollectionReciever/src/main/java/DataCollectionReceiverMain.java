import service.TestConsumeService;

public class DataCollectionReceiverMain {
    private final static String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) {
        TestConsumeService foodService = new TestConsumeService("DataCollectionReceiving", "PDFGeneration", BROKER_URL);
        foodService.run();
    }
}
