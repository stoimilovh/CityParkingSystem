import java.util.HashMap;
import java.util.stream.IntStream;

public class Parking {
    public final Long id;
    public String name;
    public String location;
    public HashMap<Integer, Floor> floors;

    public int earnings;

    public Parking(String name, String location) {
        this.id = ParkingIdGenerator.nextId();
        this.name = name;
        this.location = location;
        this.floors = new HashMap<>();
        this.earnings = 0;

        IntStream.range(1, 5).forEach(i -> floors.put(i, new Floor(i)));
    }

    public synchronized void parkVehicle(Vehicle v) throws NoAvailableSpotException {
        for (int floorNum : v.type.getAllowedFloors()) {
            Floor f = floors.get(floorNum);
            synchronized(f){
                if (f.getAvailableSpots() > 0) {
                    f.parkVehicle(v);
                    System.out.println(v.type + " parked on floor " + f.getFloorNumber());
                    return;
                }
            }
        }
        throw new NoAvailableSpotException("No available spots at the moment.");
    }

    public void unparkVehicle(String registration) {
        for (Floor f : floors.values()) {
            synchronized(f) {
                if (f.hasVehicle(registration)) {
                    Ticket t = f.getTicket(registration);
                    this.earnings += t.payment();
                    f.unparkVehicle(registration);
                    return;
                }
            }
        }
        System.out.println("No vehicle with registration " + registration + " found in parking.");
    }

}
