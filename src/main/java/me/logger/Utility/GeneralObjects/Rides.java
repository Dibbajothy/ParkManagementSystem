package me.logger.Utility.GeneralObjects;

public class Rides {

    private String rideName, minHeight, status;
    private int capacity, ticketPrice;

    public Rides(String rideName, String minHeight, int capacity, String status, int ticketPrice) {
        this.rideName = rideName;
        this.minHeight = minHeight;
        this.status = status;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
    }

    public String getRideName() {
        return rideName;
    }

    public String getMinHeight() {
        return minHeight;
    }

    public String getStatus() {
        return status;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public void setMinHeight(String minHeight) {
        this.minHeight = minHeight;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
