package com.springboot.elevator.elevatordemo.model;

/**
 * @author jagath
 * This class holds the elevator state at any time
 *
 */
public class ElevatorState {
    private final Progress progress;
    private final Direction direction;

    public ElevatorState(Progress progress, Direction direction) {
        this.progress = progress;
        this.direction = direction;
    }

    public Progress getProgress() {
        return progress;
    }

    public Direction getDirection() {
        return direction;
    }
}