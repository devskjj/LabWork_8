package models;

import java.time.LocalDateTime;
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
        for (int i = dataList.size()-1; i >= 0; i--) {
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

        public Data(String carId, LocalDateTime arrival) {
            this.carId = carId;
            this.arrival = arrival;
        }

        @Override
        public String toString() {
            return "CarId: " + carId +
                    ", arrival=" + arrival +
                    ", departure=" + departure;
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
        }
    }
}
