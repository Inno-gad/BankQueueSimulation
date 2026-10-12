
import java.util.List;

public class TestSimulation {

    public static void main(String[] args) {

        BankQueueSimulation simulation =
                new BankQueueSimulation(1, 7, 1, 6, 10);

        List<Customer> customers = simulation.simulate();

        System.out.printf(
                "%-10s %-12s %-12s %-12s %-12s %-12s %-12s%n",
                "Customer", "Inter-arrival", "Arrival",
                "Service", "Start", "Departure", "Waiting"
        );

        for (Customer c : customers) {

            System.out.printf(
                    "%-10d %-12.2f %-12.2f %-12.2f %-12.2f %-12.2f %-12.2f%n",
                    c.getCustomerNumber(),
                    c.getInterArrivalTime(),
                    c.getArrivalTime(),
                    c.getServiceTime(),
                    c.getServiceStartTime(),
                    c.getDepartureTime(),
                    c.getWaitingTime()
            );
        }
    }
}