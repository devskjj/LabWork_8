package models;

import util.Maps;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Journal {
    private final List<Data> dataList;
    private final Maps maps;

    public Journal() {
        this.dataList = new ArrayList<>();
        this.maps = new Maps();
    }

    public void saveArrival(String carId, LocalDateTime time) {
        if (carId != null) {
            dataList.add(new Data(carId, time));
        }
    }

    public void saveDeparture(String carId, LocalDateTime time) {
        for (int i = dataList.size() - 1; i >= 0; i--) {
            Data data = dataList.get(i);
            if (data.getDeparture() == null && data.getCarId().equals(carId)) {
                data.setDeparture(time);
                break;
            }
        }
    }

    public Receipt createReceipt(String carId, int rate, int minutesRate, long freeMinutes) {
        for (int i = dataList.size() - 1; i >= 0; i--) {
            Data data = dataList.get(i);
            if (data.getDeparture() != null && data.getCarId().equals(carId)) {
                Receipt receipt = data.setLogicForReceipt(rate, minutesRate, freeMinutes);
                if (receipt != null) {
                    maps.add(receipt);
                }
                return receipt;
            }
        }
        return null;
    }

    public Map<String, Long> getTopTenCars() {
        Map<String, Long> map = new HashMap<>();
        for (Data data : dataList) {
            if (data.getDeparture() != null && data.getDifference() != null) {
                map.put(data.getCarId(), map.getOrDefault(data.getCarId(), 0L) + data.getDifference().toMinutes());
            }
        }
        return map;
    }

    public void printTopTenCars() {
        maps.sortTopTenCars(getTopTenCars());
    }

    public Long getTotalByDay(LocalDate date) {
        return maps.getTotalByDate(date);
    }

    public void printStatistic() {
        maps.printStatistic();
    }

    public void printLog() {
        dataList.forEach(System.out::println);
    }

    private static class Data {
        private String carId;
        private LocalDateTime arrival;
        private LocalDateTime departure;
        private Duration difference;
        private Receipt receipt;

        public Data(String carId, LocalDateTime arrival) {
            this.carId = carId;
            this.arrival = arrival;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String departureStr = (departure != null) ? departure.format(formatter) : "не выезжал";
            String durationStr;
            if (arrival != null && departure != null) {
                Duration realDuration = Duration.between(arrival, departure);
                durationStr = String.valueOf(realDuration.toMinutes());
            } else {
                durationStr = "неизвестно";
            }
            return "Номер машины: " + carId +
                    ", Въезд: " + arrival.format(formatter) +
                    ", Выезд: " + departureStr + " (Общ. длит-сть: " + durationStr + " мин)" + "\n"
                    + ((receipt == null) ? "Оплата не взимается" : receipt.toString()) + "\n";
        }

        public Receipt setLogicForReceipt(int rate, int minutesRate, long freeMinutes) {
            if (departure == null) return null;

            LocalTime start = LocalTime.of(9, 0);
            LocalTime end = LocalTime.of(21, 0);
            boolean sameDay = arrival.toLocalDate().equals(departure.toLocalDate());

            if (setDifferenceOfDay(start, end, sameDay)) return null;
            if (difference.toMinutes() <= freeMinutes) return null;

            long toPay = difference.toMinutes();
            long totalCost = (((toPay - freeMinutes) + minutesRate - 1) / minutesRate) * rate;
            return receipt = new Receipt(carId, difference, rate, totalCost, departure.toLocalDate());
        }

        private boolean setDifferenceOfDay(LocalTime start, LocalTime end, boolean sameDay) {
            if (sameDay) {
                LocalDateTime additionalArrival = arrival.toLocalTime().isBefore(start) ? arrival.toLocalDate().atTime(start) : arrival;
                LocalDateTime departureAfterNine = departure.toLocalTime().isAfter(end) ? departure.toLocalDate().atTime(end) : departure;

                if (additionalArrival.isAfter(departureAfterNine)) return true;
                this.difference = Duration.between(additionalArrival, departureAfterNine);
            } else {
                LocalDateTime startNextDay = arrival.toLocalDate().plusDays(1).atTime(start);
                LocalDateTime endNextDay = arrival.toLocalDate().plusDays(1).atTime(end);

                if (departure.isBefore(startNextDay)) return true;
                LocalDateTime actualDeparture = departure.isAfter(endNextDay) ? endNextDay : departure;
                this.difference = Duration.between(startNextDay, actualDeparture);
            }
            return false;
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

        public Duration getDifference() {
            return difference;
        }

        public void setDeparture(LocalDateTime departure) {
            this.departure = departure;
        }

        public Receipt getReceipt() {
            return receipt;
        }
    }
}
