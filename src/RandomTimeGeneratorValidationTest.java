public class RandomTimeGeneratorValidationTest {

    public static void main(String[] args) {

        int numberOfTests = 10000;

        double minInterArrival = 1.0;
        double maxInterArrival = 7.0;

        double minService = 1.0;
        double maxService = 6.0;

        for (int i = 0; i < numberOfTests; i++) {

            double interArrival =
                    RandomTimeGenerator.generateInterArrivalTime(
                            minInterArrival, maxInterArrival
                    );

            double service =
                    RandomTimeGenerator.generateServiceTime(
                            minService, maxService
                    );

            if (interArrival < minInterArrival
                    || interArrival >= maxInterArrival) {

                throw new AssertionError(
                        "Invalid inter-arrival time: " + interArrival
                );
            }

            if (service < minService || service >= maxService) {

                throw new AssertionError(
                        "Invalid service time: " + service
                );
            }
        }

        System.out.println(
                "PASS: All " + numberOfTests
                        + " tests generated valid values."
        );
    }
}