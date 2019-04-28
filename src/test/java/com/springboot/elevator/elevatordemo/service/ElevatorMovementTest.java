package com.springboot.elevator.elevatordemo.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.springboot.elevator.elevatordemo.model.Direction;
import com.springboot.elevator.elevatordemo.model.Elevator;
import com.springboot.elevator.elevatordemo.model.Progress;
import com.springboot.elevator.elevatordemo.model.TravelRequest;

public class ElevatorMovementTest {

    @Test
    public void updateState1() {
        Elevator elevator;
        ElevatorController controller = new ElevatorController(new Elevator("A", 1), 1, 10, 20);

        controller.addRequest(new TravelRequest(4,3,6));

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 1);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 2);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 3);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 4);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 5);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.IDLE);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 6);

    }

    @Test
    public void updateState2() {
        Elevator elevator;
        ElevatorController controller = new ElevatorController(new Elevator("A", 6), 1, 10, 20);

        controller.addRequest(new TravelRequest(4,3,6));

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.DOWN);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 6);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.DOWN);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 5);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 4);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 5);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.IDLE);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 6);

    }

    @Test
    public void updateState3() {
        Elevator elevator;
        ElevatorController controller = new ElevatorController(new Elevator("A", 1), 1, 10, 20);

        controller.addRequest(new TravelRequest(4,3,10));

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 1);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 2);

        controller.addRequest(new TravelRequest(6,2,8));

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 3);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 4);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 5);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 5);
        assertTrue(elevator.getCurrentLevel() == 6);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 5);
        assertTrue(elevator.getCurrentLevel() == 7);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 8);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 9);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.IDLE);
        assertTrue(elevator.getDirection() == Direction.DOWN);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 10);

    }

    @Test
    public void updateState4() {
        Elevator elevator;
        ElevatorController controller = new ElevatorController(new Elevator("A", 1), 1, 10, 20);

        controller.addRequest(new TravelRequest(4,3,10));

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 1);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 2);

        controller.addRequest(new TravelRequest(6,2,2));

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 3);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 4);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 5);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 6);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 7);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 8);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.UP);
        assertTrue(elevator.getPassengers() == 3);
        assertTrue(elevator.getCurrentLevel() == 9);

        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.DOWN);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 10);

        elevator = controller.updateElevatorState();
        elevator = controller.updateElevatorState();
        elevator = controller.updateElevatorState();
        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.MOVING);
        assertTrue(elevator.getDirection() == Direction.DOWN);
        assertTrue(elevator.getPassengers() == 2);
        assertTrue(elevator.getCurrentLevel() == 6);

        elevator = controller.updateElevatorState();
        elevator = controller.updateElevatorState();
        elevator = controller.updateElevatorState();
        elevator = controller.updateElevatorState();
        assertTrue(elevator.getProgress() == Progress.IDLE);
        assertTrue(elevator.getDirection() == Direction.DOWN);
        assertTrue(elevator.getPassengers() == 0);
        assertTrue(elevator.getCurrentLevel() == 2);

    }

    @Test
    public void updateState5() {
        Elevator elevator;
        ElevatorController controller = new ElevatorController(new Elevator("A", 8), 1, 10, 20);

        controller.addRequest(new TravelRequest(1, 4, 6));
        controller.addRequest(new TravelRequest(2, 6, 7));
        controller.addRequest(new TravelRequest(3, 2, 8));
        controller.addRequest(new TravelRequest(5, 4, 6));
        controller.addRequest(new TravelRequest(6, 5, 1));
        controller.addRequest(new TravelRequest(8, 6, 1));
        controller.addRequest(new TravelRequest(10, 1, 4));

        while(true) {
            elevator = controller.updateElevatorState();
            if (elevator.getProgress() == Progress.IDLE) {
                break;
            }
        }

        assertTrue(elevator.getPassengers() == 0);
    }
}
