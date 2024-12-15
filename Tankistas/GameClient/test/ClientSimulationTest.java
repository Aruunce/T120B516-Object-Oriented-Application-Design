package test;

import org.junit.Test;
import clientSide.ClientGUI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientSimulationTest {

    private static final int NUM_CLIENTS = 3;
    private static long totalResponseTime = 0;
    private static int totalOperations = 0;
    private static final String OUTPUT_FILE = "response_times.txt";

    @Test
    public void testSimulateMultipleClients() throws InterruptedException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_CLIENTS);

        for (int i = 0; i < NUM_CLIENTS; i++) {
            executorService.submit(() -> {
                try {
                    simulateClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        boolean terminated = executorService.awaitTermination(10, TimeUnit.MINUTES);

        if (terminated) {
            // Calculate and print throughput
            double throughput = (double) totalOperations / (totalResponseTime / 1_000_000_000.0);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
                writer.write("Total Response Time: " + totalResponseTime + " ns\n");
                writer.write("Total Operations: " + totalOperations + "\n");
                writer.write("Throughput: " + throughput + " operations/second\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Executor service did not terminate in the specified time.");
        }
    }

    private void simulateClient() throws IOException {
        ClientGUI clientGUI = new ClientGUI();

        // Measure response time for registering
        long startTime = System.nanoTime();
        clientGUI.registerButton.doClick();
        long endTime = System.nanoTime();
        updateMetrics(endTime - startTime);

        // Measure response time for moving forward
        startTime = System.nanoTime();
        clientGUI.getClientTank().moveForward();
        endTime = System.nanoTime();
        updateMetrics(endTime - startTime);

        // Measure response time for moving left
        startTime = System.nanoTime();
        clientGUI.getClientTank().moveLeft();
        endTime = System.nanoTime();
        updateMetrics(endTime - startTime);

        // Measure response time for shooting
        startTime = System.nanoTime();
        clientGUI.getClientTank().shot();
        endTime = System.nanoTime();
        updateMetrics(endTime - startTime);
    }

    private synchronized void updateMetrics(long responseTime) {
        totalResponseTime += responseTime;
        totalOperations++;
    }
}