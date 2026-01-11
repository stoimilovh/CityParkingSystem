import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

public class Ticket {
    private final Long id;
    private Integer floorNumber;
    private String vehicleRegistration;
    private VehicleType vehicleType;
    private LocalTime startTime;
    private LocalTime endTime;

    public Ticket(Integer floorNumber, String vehicleRegistration, VehicleType vehicleType) {
        this.id = TicketIdGenerator.nextId();
        this.floorNumber = floorNumber;
        this.vehicleRegistration = vehicleRegistration;
        this.vehicleType = vehicleType;
        this.startTime = LocalTime.now();
    }

    public int payment(){
        this.endTime = LocalTime.now();
        Duration duration = Duration.between(startTime, endTime);

        long hours = duration.toMinutes() / 60;
        if (duration.toMinutes() % 60 > 0) {
            hours += 1;
        }

        switch (vehicleType) {
            case CAR, MOTORCYCLE -> { return (int) hours * 20; }
            case BUS -> { return (int) hours * 60; }
            case TRUCK -> { return (int) hours * 100; }
            default -> { return 0; }
        }

    }
    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

}
