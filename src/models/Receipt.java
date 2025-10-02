package models;

import java.time.Duration;

public class Receipt {
    private String carId;
    private Duration duration;
    private int cost;
    private long totalCost;

    public Receipt(String carId, Duration duration, int cost, long totalCost) {
        this.carId = carId;
        this.duration = duration;
        this.cost = cost;
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Чек: " + carId +
                ", Время стоянки c 9 утра до 9 вечера: " + duration.toMinutes() + " мин" +
                ", Тариф: " + cost +
                ", Итого к оплате: " + totalCost;
    }
}
