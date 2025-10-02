package models;

import java.time.Duration;
import java.time.LocalDate;

public class Receipt {
    private String carId;
    private Duration duration;
    private int cost;
    private long totalCost;
    private LocalDate receiptDate;

    public Receipt(String carId, Duration duration, int cost, long totalCost, LocalDate receiptDate) {
        this.carId = carId;
        this.duration = duration;
        this.cost = cost;
        this.totalCost = totalCost;
        this.receiptDate = receiptDate;
    }

    @Override
    public String toString() {
        return "Чек: " + carId +
                ", Время стоянки c 9 утра до 9 вечера: " + duration.toMinutes() + " мин" +
                ", Тариф: " + cost +
                ", Итого к оплате: " + totalCost;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }
}
