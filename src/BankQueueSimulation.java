
import java.util.ArrayList;
import java.util.List;

public class BankQueueSimulation {

    private double minInterArrival;
    private double maxInterArrival;
    private double minService;
    private double maxService;
    private int numberOfCustomers;

    public BankQueueSimulation(double minInterArrival,
                               double maxInterArrival,
                               double minService,
                               double maxService,
                               int numberOfCustomers) {

        if (minInterArrival >= maxInterArrival) {
            throw new IllegalArgumentException(
                    "Minimum inter-arrival time must be less than maximum."
            );
        }

        if (minService >= maxService) {
            throw new IllegalArgumentException(
                    "Minimum service time must be less than maximum."
            );
        }

        if (numberOfCustomers < 1) {
            throw new IllegalArgumentException(
                    "Number of customers must be at least 1."
            );
        }

        if (minInterArrival < 0 || minService < 0) {
            throw new IllegalArgumentException(
                    "Time values cannot be negative."
            );
        }

        this.minInterArrival = minInterArrival;
        this.maxInterArrival = maxInterArrival;
        this.minService = minService;
        this.maxService = maxService;
        this.numberOfCustomers = numberOfCustomers;
    }

    public List<Customer> simulate() {

        List<Customer> customers = new ArrayList<>();

        double previousArrivalTime = 0.0;
        double previousDepartureTime = 0.0;

        for (int i = 1; i <= numberOfCustomers; i++) {

            double interArrivalTime;
            double arrivalTime;

            // Assume the first customer arrives at time zero.
            if (i == 1) {
                interArrivalTime = 0.0;
                arrivalTime = 0.0;
            } else {
                interArrivalTime =
                        RandomTimeGenerator.generateInterArrivalTime(
                                minInterArrival,
                                maxInterArrival
                        );

                arrivalTime = previousArrivalTime + interArrivalTime;
            }

            // Generate the customer's service time.
            double serviceTime =
                    RandomTimeGenerator.generateServiceTime(
                            minService,
                            maxService
                    );

            // Service starts when both the customer and the server are ready.
            double serviceStartTime =
                    Math.max(arrivalTime, previousDepartureTime);

            // Calculate when service finishes.
            double departureTime = serviceStartTime + serviceTime;

            // Calculate waiting time and total time in the system.
            double waitingTime = serviceStartTime - arrivalTime;

            double timeInSystem = departureTime - arrivalTime;

            // Store the customer's simulation results.
            Customer customer = new Customer(
                    i,
                    interArrivalTime,
                    arrivalTime,
                    serviceTime,
                    serviceStartTime,
                    departureTime,
                    waitingTime,
                    timeInSystem
            );

            customers.add(customer);

            // Update values for the next customer.
            previousArrivalTime = arrivalTime;
            previousDepartureTime = departureTime;
        }

        return customers;
    }
}