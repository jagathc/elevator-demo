package com.springboot.elevator.elevatordemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author jagath
 * This is the model class to represent an elevator object
 */
public class Elevator {

    private String name;
    private int currentLevel = 1;
    private int passengers;
    @JsonIgnore
    private ElevatorState state = new ElevatorState(MovementState.IDLE, Direction.UP);

    public Elevator(String name, int passengers, int currentLevel, ElevatorState state) {
        this.passengers = passengers;
        this.currentLevel = currentLevel;
        this.name = name;
        this.state = state;
    }

    public Elevator(String name, int currentLevel) {
        this.currentLevel = currentLevel;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getPassengers() {
        return passengers;
    }

    public ElevatorState getState() {
        return state;
    }

    public MovementState getProgress() {
        return state.getProgress();
    }

    public Direction getDirection() {
        return state.getDirection();
    }
}
