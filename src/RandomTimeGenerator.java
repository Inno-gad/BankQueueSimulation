import java.util.Random;

public class RandomTimeGenerator {

    private static final Random random = new Random();

    // Generate a uniform random decimal within a specified range
    public static double generateUniform(double min, double max) {

        if (min >= max) {
            throw new IllegalArgumentException(
                    "Minimum value must be less than maximum value."
            );
        }

        return min + random.nextDouble() * (max - min);
    }

    // Generate a random inter-arrival time
    public static double generateInterArrivalTime(
            double minInterArrival,
            double maxInterArrival) {

        return generateUniform(minInterArrival, maxInterArrival);
    }

    // Generate a random service time
    public static double generateServiceTime(
            double minService,
            double maxService) {

        return generateUniform(minService, maxService);
    }
}