import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;

public class ParkingDataManager {

    private static final String FILE_NAME = "parking.json";

    public static void save(Parking parking) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalTime.class, new LocalTimeAdapter()).create();
            String json = gson.toJson(parking);
            Files.writeString(Paths.get(FILE_NAME), json);
            System.out.println("Parking data saved.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Parking load() {
        try {
            if (!Files.exists(Paths.get(FILE_NAME))) {
                return null;
            }
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                    .create();
            String json = Files.readString(Paths.get(FILE_NAME));
            return gson.fromJson(json, Parking.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
