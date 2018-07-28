package vehicle;

import utils.Config;

public class Bike implements Vehicle {
    @Override
    public int getCapacity() {
        return Integer.parseInt(System.getProperty(Config.getInstance().getSetting("BIKE_PARKING_CAPACITY")));
    }

    @Override
    public int getVacant() {
        return 0;
    }

    @Override
    public void setInTime(long inTime) {

    }

    @Override
    public long getInTime() {
        return 0;
    }

    @Override
    public void setoutTime(long outTime) {

    }

    @Override
    public long getOutTime() {
        return 0;
    }

    @Override
    public String getVehicleNo() {
        return null;
    }
}
