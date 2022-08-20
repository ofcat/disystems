import service.StationDataCollectionService;

public class Main {
    private final static String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) {
        StationDataCollectionService foodService = new StationDataCollectionService("StationDataGathering", "DONE", BROKER_URL);
        foodService.run();
    }
}
