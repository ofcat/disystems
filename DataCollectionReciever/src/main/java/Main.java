import service.TestConsumeService;

public class Main {
    private final static String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) {
        TestConsumeService foodService = new TestConsumeService("DataGathering", "DONE", BROKER_URL);
        foodService.run();
    }
}
