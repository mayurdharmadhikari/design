import exitmachine.ExitMachine;
import exitmachine.MainExit;
import parkinglot.ParkingLot;
import vehicle.VehicleType;
import vendingmachine.FrontEntranceMachine;
import vendingmachine.VendingMachine;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ParkingLot.getInstance().init();

        VendingMachine fronEntrance = new FrontEntranceMachine();

        String token = fronEntrance.allocateParking(VehicleType.CAR, System.currentTimeMillis(), "234");
        String t1 = fronEntrance.allocateParking(VehicleType.CAR, System.currentTimeMillis(), "345");
        String t2 = fronEntrance.allocateParking(VehicleType.CAR, System.currentTimeMillis(), "456");

        VendingMachine et2 = new FrontEntranceMachine();
        et2.allocateParking(VehicleType.CAR, System.currentTimeMillis(), "123");
        et2.allocateParking(VehicleType.CAR, System.currentTimeMillis(), "376");
        ExitMachine machine = new MainExit();
        machine.calculateCharges(token);
        et2.allocateParking(VehicleType.CAR, System.currentTimeMillis(), "376");
    }
}