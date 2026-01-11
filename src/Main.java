import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Parking parking = ParkingDataManager.load();
        if (parking == null) {
            parking = new Parking("City Parking", "Kocani");
        }


        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> parkVehicle(parking);
                case 2 -> unparkVehicle(parking);
                case 3 -> showStatus(parking);
                case 0 -> {
                    ParkingDataManager.save(parking);
                    running = false;
                    System.out.println("Exiting system...");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== PARKING SYSTEM ===");
        System.out.println("1. Park vehicle");
        System.out.println("2. Unpark vehicle");
        System.out.println("3. Show parking status");
        System.out.println("0. Exit");
    }

    private static void parkVehicle(Parking parking) {
        String registration = readString("Enter vehicle registration number: ");
        VehicleType type = readVehicleType();

        Vehicle vehicle = new Vehicle(registration, type);

        try {
            parking.parkVehicle(vehicle);
            System.out.printf("Vehicle %s (%s) parked successfully.%n", registration, type);
        } catch (NoAvailableSpotException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void unparkVehicle(Parking parking) {
        String registration = readString("Enter vehicle registration number: ");

        parking.unparkVehicle(registration);

        System.out.printf("Vehicle %s unparked.%n", registration);
    }

    private static void showStatus(Parking parking) {
        System.out.println("\n--- Parking Status ---");
        parking.floors.forEach((floorNum, floor) ->
                System.out.println(
                        "Floor " + floorNum +
                                " | Available spots: " + floor.getAvailableSpots()
                )
        );
        System.out.println("Total earnings: " + parking.earnings);
    }

    private static VehicleType readVehicleType() {
        while (true) {
            System.out.println("Choose vehicle type:");
            System.out.println("1. CAR");
            System.out.println("2. MOTORCYCLE");
            System.out.println("3. BUS");
            System.out.println("4. TRUCK");

            System.out.print("Type: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            int option = scanner.nextInt();

            switch (option) {
                case 1: return VehicleType.CAR;
                case 2: return VehicleType.MOTORCYCLE;
                case 3: return VehicleType.BUS;
                case 4: return VehicleType.TRUCK;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }


    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Enter a number: ");
        }
        return scanner.nextInt();
    }

    private static long readLong(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextLong()) {
            scanner.next();
            System.out.print("Enter a number: ");
        }
        return scanner.nextLong();
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

}
