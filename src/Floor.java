import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Floor {
    private final int floorNumber;
    private int availableSpots;
    private HashMap<String, Ticket> tickets;
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.availableSpots = 40;
        this.tickets = new HashMap<>();
    }
     public synchronized void parkVehicle(Vehicle v) {
        Ticket t = new Ticket(this.floorNumber, v.registration, v.type);
        tickets.put(t.getVehicleRegistration(), t);
        availableSpots--;
     }

     public synchronized void unparkVehicle(String registration) {
         this.tickets.remove(registration);
         availableSpots++;
     }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean hasVehicle(String registration) {
        return tickets.containsKey(registration);
    }

    public synchronized Ticket getTicket(String registration) {
        return tickets.get(registration);
    }

}
