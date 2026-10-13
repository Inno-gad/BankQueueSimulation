import java.util.List;

public class QueueStatistics {
    private final List<Customer> customers;
    private final int totalCustomers;

    private double avgInterArrivalTime;
    private double avgServiceTime;
    private double avgWaitingTime;
    private double maxWaitingTime;
    private double avgTimeInSystem;
    private double percentageWhoWaited;
    private double serverUtilization;

    public QueueStatistics(List<Customer> customers) {
        this.customers = customers;
        this.totalCustomers = customers != null ? customers.size() : 0;
        if (this.totalCustomers > 0) {
            calculateStatistics();
        }
    }

    private void calculateStatistics() {
        double totalIat = 0.0;
        double totalServiceTime = 0.0;
        double totalWaitingTime = 0.0;
        double totalTimeInSystem = 0.0;
        double maxWait = 0.0;
        int countWaited = 0;

        for (Customer c : customers) {
            totalIat += c.getInterArrivalTime();
            totalServiceTime += c.getServiceTime();
            totalWaitingTime += c.getWaitingTime();
            totalTimeInSystem += c.getTimeInSystem();

            if (c.getWaitingTime() > 0) {
                countWaited++;
            }
            if (c.getWaitingTime() > maxWait) {
                maxWait = c.getWaitingTime();
            }
        }

        // Formulas based on lecture specifications[cite: 1]
        this.avgInterArrivalTime = totalIat / totalCustomers; // Total IAT / Total number of customers[cite: 1]
        this.avgServiceTime = totalServiceTime / totalCustomers;
        this.avgWaitingTime = totalWaitingTime / totalCustomers;
        this.maxWaitingTime = maxWait;
        this.avgTimeInSystem = totalTimeInSystem / totalCustomers; // Total time spent in system / Total no. of customers[cite: 1]
        this.percentageWhoWaited = ((double) countWaited / totalCustomers) * 100.0;

        // Server utilization = (Total busy time / Total simulation duration) * 100
        double totalSimulationDuration = customers.get(totalCustomers - 1).getDepartureTime();
        this.serverUtilization = totalSimulationDuration > 0 ? (totalServiceTime / totalSimulationDuration) * 100.0 : 0.0;
    }

    // Individual metric getters
    public int getTotalCustomers() { return totalCustomers; }
    public double getAvgInterArrivalTime() { return avgInterArrivalTime; }
    public double getAvgServiceTime() { return avgServiceTime; }
    public double getAvgWaitingTime() { return avgWaitingTime; }
    public double getMaxWaitingTime() { return maxWaitingTime; }
    public double getAvgTimeInSystem() { return avgTimeInSystem; }
    public double getPercentageWhoWaited() { return percentageWhoWaited; }
    public double getServerUtilization() { return serverUtilization; }

    // Formatted summary for Member 3's output window[cite: 1]
    public String getFormattedSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== QUEUE PERFORMANCE STATISTICS =====\n");
        sb.append(String.format("Total Customers Simulated: %d\n", totalCustomers));
        sb.append(String.format("Average Inter-arrival Time: %.2f mins\n", avgInterArrivalTime));
        sb.append(String.format("Average Service Time:       %.2f mins\n", avgServiceTime));
        sb.append(String.format("Average Waiting Time:       %.2f mins\n", avgWaitingTime));
        sb.append(String.format("Maximum Waiting Time:       %.2f mins\n", maxWaitingTime));
        sb.append(String.format("Average Time in System:     %.2f mins\n", avgTimeInSystem));
        sb.append(String.format("Percentage Who Waited:      %.2f%%\n", percentageWhoWaited));
        sb.append(String.format("Server Utilization:         %.2f%%\n", serverUtilization));
        sb.append("=========================================\n");
        return sb.toString();
    }
}