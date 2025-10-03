package models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Journal {
    private final List<Data> dataList;

    public Journal() {
        this.dataList = new ArrayList<>();
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

    public void printLog() {
        dataList.forEach(System.out::println);
    }

    private static class Data {
        private String carId;
        private LocalDateTime arrival;
        private LocalDateTime departure;
        private Duration difference;

        public Data(String carId, LocalDateTime arrival) {
            this.carId = carId;
            this.arrival = arrival;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String departureStr = (departure != null) ? departure.format(formatter) : "не выезжал";
            String durationStr = (difference != null) ? String.valueOf(difference.toMinutes()) : "неизвестно";
            return "Номер машины: " + carId +
                    ", Въезд: " + arrival.format(formatter) +
                    ", Выезд: " + departureStr + " *** длительность парковки: " + durationStr + " мин";
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

        public void setDeparture(LocalDateTime departure) {
            this.departure = departure;
            this.difference = Duration.between(this.arrival, this.departure);
        }
    }
}
