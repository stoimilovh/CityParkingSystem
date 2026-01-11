public enum VehicleType {
    CAR(new int[]{4, 3, 2, 1}), // can park on every floor
    MOTORCYCLE(new int[]{4, 3, 2, 1}), // can park on every floor
    BUS(new int[]{2, 1}), // can park on first and second floor
    TRUCK(new int[]{1}); // only parks on first floor

    private final int[] allowedFloors;

    VehicleType(int[] allowedFloors) {
        this.allowedFloors = allowedFloors;
    }

    public int[] getAllowedFloors() {
        return allowedFloors;
    }
}
