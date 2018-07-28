package exitmachine;

public interface ExitMachine {
    int MORNING_COST = 30;
    int EVENING_COST = 50;
    void calculateCharges(String token);
}
