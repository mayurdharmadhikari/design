package exitmachine;

import parkinglot.ParkingLot;
import vehicle.Vehicle;
import vendingmachine.FrontEntranceMachine;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainExit implements ExitMachine {

    public interface Callback {
        void onParkingAvailable();
    }

    private Callback callback = new FrontEntranceMachine();

    @Override
    public void calculateCharges(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Ticket id cannot be null");
        }

        Vehicle vehicle = ParkingLot.getInstance().car.get(token);
        boolean isParkingFull = ParkingLot.getInstance().getCarParkingCount().get() == ParkingLot.getInstance().getCarParkingCapacity();
        ParkingLot.getInstance().car.remove(token);

        long outTime = System.currentTimeMillis();
        long inTime = vehicle.getInTime();

        int cost = 0;
        // Same day exit
        if (DateFormat.getDateInstance().format(new Date(inTime)).equalsIgnoreCase(DateFormat.getDateInstance().format(new Date(outTime)))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            date.setTime(System.currentTimeMillis());
            System.out.println(dateFormat.format(date));
            try {
                if (date.before(dateFormat.parse("13:00"))) {
                    System.out.println("Current time greater than 13.00");
                    cost = cost + ExitMachine.MORNING_COST;
                } else {
                    cost = cost + ExitMachine.EVENING_COST;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (isParkingFull) {
            callback.onParkingAvailable();
        } 
        System.out.println("Vehicle Details:" + vehicle.getVehicleNo() + "\n" + "Entry Time:" + formatDate(inTime) + "\n" + "Exit Time:" + formatDate(outTime) + "\n" + "Cost:" + cost);
        ParkingLot.getInstance().setCarParkingCount(1);
    }

    private String formatDate(long dateInMillis) {
        Date date = new Date(dateInMillis);
        return DateFormat.getDateTimeInstance().format(date);
    }
}
