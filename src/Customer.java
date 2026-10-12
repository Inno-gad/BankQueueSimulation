
public class Customer {

    private int customerNumber;
    private double interArrivalTime;
    private double arrivalTime;
    private double serviceTime;
    private double serviceStartTime;
    private double departureTime;
    private double waitingTime;
    private double timeInSystem;

    public Customer(int customerNumber,
                    double interArrivalTime,
                    double arrivalTime,
                    double serviceTime,
                    double serviceStartTime,
                    double departureTime,
                    double waitingTime,
                    double timeInSystem) {

        this.customerNumber = customerNumber;
        this.interArrivalTime = interArrivalTime;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.serviceStartTime = serviceStartTime;
        this.departureTime = departureTime;
        this.waitingTime = waitingTime;
        this.timeInSystem = timeInSystem;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public double getInterArrivalTime() {
        return interArrivalTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public double getServiceStartTime() {
        return serviceStartTime;
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public double getTimeInSystem() {
        return timeInSystem;
    }
}