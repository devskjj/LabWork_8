package models;

import java.time.Duration;
import java.time.LocalDateTime;

public class Receipt {
    private final String carId;
    private final LocalDateTime arrival;
    private final LocalDateTime departure;
    private final int paidMinutes;
    private final double costPerMinute = 0.10;
    private final double totalCost;

    public Receipt(String carId, LocalDateTime arrival, LocalDateTime departure) {
        this.carId = carId;
        this.arrival = arrival;
        this.departure = departure;
        this.paidMinutes = calculatePaidMinutes();
        this.totalCost = paidMinutes * costPerMinute;
    }


    private int calculatePaidMinutes() {

        long totalDurationMinutes = Duration.between(arrival, departure).toMinutes();


        if (totalDurationMinutes <= 30) {
            return 0;
        }

        int paidMinutes = 0;
        LocalDateTime currentTime = arrival;

        while (currentTime.isBefore(departure)) {
            int hour = currentTime.getHour();


            if (hour >= 9 && hour < 20) {
                paidMinutes++;
            }

            currentTime = currentTime.plusMinutes(1);
        }

        return paidMinutes;
    }

    @Override
    public String toString() {
        return String.format("ЧЕК ОПЛАТЫ | Стоянка: %d мин | Оплач. время: %d мин | СТОИМОСТЬ: %.2f руб.",
                Duration.between(arrival, departure).toMinutes(),
                paidMinutes,
                totalCost);
    }


    public double getTotalCost() {
        return totalCost;
    }

    public String getCarId() {
        return carId;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }
}