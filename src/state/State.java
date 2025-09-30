package state;

import models.Car;
import util.RandomChance;

public enum State {
    ON_ROUTE("В ДВИЖЕНИИ") {
        @Override
        public void changeState(Car car) {
            if (RandomChance.getRandom(3)) {
                car.setState(ON_PARK);
            }
        }
    },
    ON_PARK("НА ПАРКОВКЕ") {
        @Override
        public void changeState(Car car) {
            if (RandomChance.getRandom(3)) {
                car.setState(ON_ROUTE);
            }
        }
    };

    private final String value;

    State(String value) {
        this.value = value;
    }

    public abstract void changeState(Car car);

    public String getValue() {
        return value;
    }
}
