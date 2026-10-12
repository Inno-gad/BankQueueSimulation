public class RandomTimeGeneratorTest {

    public static void main(String[] args) {

        double minInterArrival = 1.0;
        double maxInterArrival = 7.0;

        double minService = 1.0;
        double maxService = 6.0;

        System.out.println("BANK QUEUE SIMULATION");
        System.out.println("=====================");

        System.out.printf(
                "Inter-arrival range: %.2f - %.2f minutes%n",
                minInterArrival, maxInterArrival
        );

        System.out.printf(
                "Service-time range: %.2f - %.2f minutes%n%n",
                minService, maxService
        );

        System.out.printf(
                "%-12s %-22s %-18s%n",
                "Customer", "Inter-arrival Time", "Service Time"
        );

        System.out.println(
                "----------------------------------------------------"
        );

        for (int i = 1; i <= 10; i++) {

            double interArrival =
                    RandomTimeGenerator.generateInterArrivalTime(
                            minInterArrival, maxInterArrival
                    );

            double service =
                    RandomTimeGenerator.generateServiceTime(
                            minService, maxService
                    );

            System.out.printf(
                    "%-12d %-22.2f %-18.2f%n",
                    i, interArrival, service
            );
        }
    }
}