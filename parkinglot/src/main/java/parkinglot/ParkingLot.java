package parkinglot;

import vehicle.Vehicle;
import vehicle.VehicleType;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class ParkingLot {
    private static ParkingLot parkingLot;
    private int carParkingCapacity = 4;//Integer.parseInt(System.getProperty(Config.getInstance().getSetting("CAR_PARKING_CAPACITY")));
    private boolean isInitialised = false;
    private AtomicInteger carParkingCount = new AtomicInteger();
    public ConcurrentHashMap<String, Vehicle> bike;
    public ConcurrentHashMap<String, Vehicle> car;

    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            //synchronized block to remove overhead
            synchronized (ParkingLot.class) {
                if (parkingLot == null) {
                    // if instance is null, initialize
                    parkingLot = new ParkingLot();
                }
            }
        }
        return parkingLot;
    }

    private ParkingLot() {
    }

    public void init() {
        car = new ConcurrentHashMap<>();
        isInitialised = true;
    }

    public void setCarParkingCount(int value) {
        if (value == 0) {
            carParkingCount.set(carParkingCount.get() + 1);
        } else if (value == 1) {
            if (carParkingCount.get() > 0)
                carParkingCount.set(carParkingCount.get() - 1);
        }
    }

    public String generateToken(int vehicleType) {
        UUID uuid = UUID.randomUUID();
        String random = uuid.toString();
        String token = null;
        switch (vehicleType) {
            case VehicleType.CAR:
                token = "CR" + String.valueOf(random);
                break;
            case VehicleType.BIKE:
                token = "BK" + String.valueOf(random);
                break;
        }
        return token;
    }

    public int getCarParkingCapacity() {
        return carParkingCapacity;
    }

    public AtomicInteger getCarParkingCount() {
        return carParkingCount;
    }

    public boolean isInitialised() {
        return isInitialised;
    }
}
