package state;

import models.Car;
import models.Journal;
import models.Park;
import util.RandomChance;

import java.time.LocalDateTime;

public enum State {
    ON_ROUTE("В ДВИЖЕНИИ") {
        @Override
        public void changeState(Car car, Park park, Journal journal, LocalDateTime time) {
            if (park.getCount() > 0) {
                if (RandomChance.getRandom(3)) {
                    car.setState(ON_PARK);
                    park.decreaseCount();
                    journal.saveArrival(car.getCarId(), time);
                }
            }
        }
    },
    ON_PARK("НА ПАРКОВКЕ") {
        @Override
        public void changeState(Car car, Park park, Journal journal, LocalDateTime time) {
            if (RandomChance.getRandom(3)) {
                car.setState(ON_ROUTE);
                park.increaseCount();
                journal.saveDeparture(car.getCarId(), time);
                journal.createReceipt(car.getCarId(), 10, 5, 30);
            }
        }
    };

    private final String value;

    State(String value) {
        this.value = value;
    }

    public abstract void changeState(Car car, Park park, Journal journal, LocalDateTime time);
}
