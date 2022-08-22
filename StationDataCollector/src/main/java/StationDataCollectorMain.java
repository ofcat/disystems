import service.StationDataCollectionService;

public class StationDataCollectorMain {
    private final static String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) {
        StationDataCollectionService foodService = new StationDataCollectionService("StationDataGathering", "DataCollectionReceiving", BROKER_URL);
        foodService.run();
    }
}
