import service.DataCollectionReceiverService;

public class DataCollectionReceiverMain {
    private final static String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) {
        DataCollectionReceiverService foodService = new DataCollectionReceiverService("DataCollectionReceiving", "PDFGeneration", BROKER_URL);
        foodService.run();
    }
}
