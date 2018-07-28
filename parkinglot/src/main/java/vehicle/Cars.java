package vehicle;

import utils.Config;

public class Cars implements Vehicle {
    private final String vehicleNo;
    private long inTime;
    private long outTime;

    public Cars(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    @Override
    public int getCapacity() {
        return Integer.parseInt(System.getProperty(Config.getInstance().getSetting("CAR_PARKING_CAPACITY")));
    }

    @Override
    public int getVacant() {
        return 0;
    }

    @Override
    public void setInTime(long inTime) {
        this.inTime = inTime;
    }

    @Override
    public long getInTime() {
        return inTime;
    }

    @Override
    public void setoutTime(long outTime) {
        this.outTime = outTime;
    }

    @Override
    public long getOutTime() {
        return outTime;
    }

}
