import java.util.List;

public class SimulationTest {

    public static void main(String[] args) {
        System.out.println("--- Running Bank Simulation Automated Tests ---\n");

        double minIat = 1.0;
        double maxIat = 7.0;
        double minSt = 1.0;
        double maxSt = 6.0;
        int numCustomers = 20;

        // Run simulation logic
        BankQueueSimulation sim = new BankQueueSimulation(minIat, maxIat, minSt, maxSt, numCustomers);
        List<Customer> customers = sim.simulate(); // Updated method name
        QueueStatistics stats = new QueueStatistics(customers);

        boolean passed = true;

        // Verification 1: Inter-arrival times stay within bounds
        for (Customer c : customers) {
            if (c.getInterArrivalTime() < minIat || c.getInterArrivalTime() > maxIat) {
                System.err.println("Test Failed: IAT out of range for Customer " + c.getCustomerNumber());
                passed = false;
            }
        }

        // Verification 2: Service times stay within bounds
        for (Customer c : customers) {
            if (c.getServiceTime() < minSt || c.getServiceTime() > maxSt) {
                System.err.println("Test Failed: Service time out of range for Customer " + c.getCustomerNumber());
                passed = false;
            }
        }

        // Verification 3: Service start time is never before arrival time
        for (Customer c : customers) {
            if (c.getServiceStartTime() < c.getArrivalTime()) {
                System.err.println("Test Failed: Customer " + c.getCustomerNumber() + " served before arrival.");
                passed = false;
            }
        }

        // Verification 4: Waiting times are non-negative
        for (Customer c : customers) {
            if (c.getWaitingTime() < 0) {
                System.err.println("Test Failed: Negative wait time for Customer " + c.getCustomerNumber());
                passed = false;
            }
        }

        if (passed) {
            System.out.println("ALL SYSTEM TESTS PASSED SUCCESSFULLY!\n");
            System.out.println(stats.getFormattedSummary());
        }
    }
}