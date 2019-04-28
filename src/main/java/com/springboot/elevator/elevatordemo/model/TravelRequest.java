package com.springboot.elevator.elevatordemo.model;

import static com.springboot.elevator.elevatordemo.model.Direction.UP;
import static com.springboot.elevator.elevatordemo.model.Direction.DOWN;

/**
 * @author jagath
 * This is the new elevator request from the passenger
 *
 */
public class TravelRequest {
    private Integer level;
    private Integer passengers;
    private Integer destination;

    private Integer passengersInElevator = 0;

    public TravelRequest() {
    }

    public TravelRequest(Integer level, Integer passengers, Integer destination) {
        this.level = level;
        this.passengers = passengers;
        this.destination = destination;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public Integer getDestination() {
        return destination;
    }

    public Direction getDirection() {
        return destination > level ? UP : DOWN;
    }

    public Integer getPassengersInElevator() {
        return passengersInElevator;
    }

    public void setPassengersInElevator(Integer passengersInElevator) {
        this.passengersInElevator = passengersInElevator;
    }

    public Integer getRemainingPassengers() {
        return passengers - passengersInElevator;
    }
}
