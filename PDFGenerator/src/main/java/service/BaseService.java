package service;

import com.itextpdf.text.DocumentException;
import communication.Consumer;
import communication.Producer;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class BaseService implements Runnable{

    private final String inDestination;
    private final String outDestination;
    private final String brokerUrl;

    public BaseService(String inDestination, String outDestination, String brokerUrl) {
        this.inDestination = inDestination;
        this.outDestination = outDestination;
        this.brokerUrl = brokerUrl;
    }

    @Override
    public void run() {
        while (true) {
            try {
                execute(inDestination, outDestination, brokerUrl);
            } catch (DocumentException | IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void execute(String inDestination, String outDestination, String brokerUrl) throws DocumentException, IOException, URISyntaxException {
        String input = Consumer.receive(inDestination, 10000, brokerUrl);

        if (null == input) {
            return;
        }

        String output = executeInternal(input);
        Producer.send(output, outDestination, brokerUrl);
    }

    protected abstract String executeInternal(String input) throws DocumentException, IOException, URISyntaxException;
}
