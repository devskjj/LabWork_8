package state;

import util.RandomChance;

public enum State {
    ON_ROUTE("В ДВИЖЕНИИ") {
        @Override
        public void changeState() {

        }
    },
    ON_PARK("НА ПАРКОВКЕ") {
        @Override
        public void changeState() {
            RandomChance.getRandom(3);
        }
    };

    State(String value) {
    }

    public abstract void changeState();
}
