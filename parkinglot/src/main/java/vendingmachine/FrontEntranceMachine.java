package vendingmachine;

import exception.NotInitialisedException;
import exitmachine.MainExit;
import parkinglot.ParkingLot;
import vehicle.Cars;
import vehicle.Vehicle;
import vehicle.VehicleType;

public class FrontEntranceMachine implements VendingMachine, MainExit.Callback {

    @Override
    public String allocateParking(int type, long inTime, String vehicleNo) {
        if (!ParkingLot.getInstance().isInitialised()) {
            throw new NotInitialisedException("Parking Lot System is not initialised");
        }

        if (ParkingLot.getInstance().getCarParkingCount().get() < ParkingLot.getInstance().getCarParkingCapacity()) {
            String token = null;
            if (type == VehicleType.CAR) {
                Vehicle vehicle = new Cars(vehicleNo);
                vehicle.setInTime(System.currentTimeMillis());
                token = ParkingLot.getInstance().generateToken(VehicleType.CAR);
                ParkingLot.getInstance().car.put(token, vehicle);
                ParkingLot.getInstance().setCarParkingCount(0);
            }
            System.out.println("Vehicle entered:" + vehicleNo + ":Token" + token);
            return token;
        }
        System.out.println("Parking full");
        return null;
    }

    @Override
    public void onParkingAvailable() {
        System.out.println("Parking is now available");
    }
}