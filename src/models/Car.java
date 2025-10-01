package models;

import state.State;

import java.time.LocalDateTime;
import java.util.UUID;

public class Car {
    private final String carId;
    private State state;

    public Car() {
        this.carId = "KG-" + UUID.randomUUID().toString().substring(0, 4);
        this.state = State.ON_ROUTE;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                '}';
    }

    public void update(Park park, Journal journal, LocalDateTime time) {
        state.changeState(this, park, journal, time);
    }

    public void showCar() {
        System.out.println(state.getValue() + " " + carId);
    }

    public String getCarId() {
        return carId;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
