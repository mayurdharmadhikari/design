package vehicle;

public interface Vehicle {
    int getCapacity();

    int getVacant();

    void setInTime(long inTime);

    long getInTime();

    void setoutTime(long outTime);

    long getOutTime();

    String getVehicleNo();
}
