package com.springboot.elevator.elevatordemo.model;

/**
 * @author jagath
 * This class holds the elevator state at any time
 *
 */
public class ElevatorState {
    private final MovementState progress;
    private final Direction direction;

    public ElevatorState(MovementState progress, Direction direction) {
        this.progress = progress;
        this.direction = direction;
    }

    public MovementState getProgress() {
        return progress;
    }

    public Direction getDirection() {
        return direction;
    }
}