package models;

import state.State;

import java.util.UUID;

public class Car {
    private String carId;
    private State state;

    public Car() {
        this.carId = "KG-" + UUID.randomUUID().toString().substring(0,4);
        this.state = State.ON_ROUTE;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                '}';
    }

    public String getCarId() {
        return carId;
    }
}
