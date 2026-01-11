import java.util.concurrent.atomic.AtomicLong;

public class ParkingIdGenerator {
    private static final AtomicLong counter = new AtomicLong(1);

    public static long nextId() {
        return counter.getAndIncrement();
    }
}
