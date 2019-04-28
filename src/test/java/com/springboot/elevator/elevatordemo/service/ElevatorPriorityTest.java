package com.springboot.elevator.elevatordemo.service;

import org.junit.Assert;
import org.junit.Test;

import com.springboot.elevator.elevatordemo.model.Elevator;
import com.springboot.elevator.elevatordemo.model.ElevatorState;
import com.springboot.elevator.elevatordemo.model.Progress;
import com.springboot.elevator.elevatordemo.model.TravelRequest;

import static com.springboot.elevator.elevatordemo.model.Direction.UP;
import static com.springboot.elevator.elevatordemo.model.Direction.DOWN;

public class ElevatorPriorityTest {

    @Test
    public void isMovingTowardsLevel_moving_towards_the_request_UP_returns_true() {
        ElevatorController controller = new ElevatorController(new Elevator("A", 0, 5, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20);

        Assert.assertTrue(controller.isMovingTowardsLevel(7));
    }

    @Test
    public void isMovingTowardsLevel_moving_towards_the_request_DOWN_returns_true() {
        ElevatorController controller = new ElevatorController(new Elevator("A", 0, 5, new ElevatorState(Progress.MOVING, DOWN)), 1, 10, 20);

        Assert.assertTrue(controller.isMovingTowardsLevel(4));
    }

    @Test
    public void isMovingTowardsLevel_moving_opposite_the_request_DOWN_returns_true() {
        ElevatorController controller = new ElevatorController(new Elevator("A", 0, 5, new ElevatorState(Progress.MOVING, DOWN)), 1, 10, 20);

        Assert.assertFalse(controller.isMovingTowardsLevel(7));
    }

    @Test
    public void isMovingTowardsLevel_moving_opposite_the_request_UP_returns_true() {
        ElevatorController controller = new ElevatorController(new Elevator("A", 0, 5, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20);

        Assert.assertFalse(controller.isMovingTowardsLevel(4));
    }

    @Test
    public void isMovingTowardsLevel_elevator_stopped_returns_false() {
        ElevatorController controller = new ElevatorController(new Elevator("A", 0, 5, new ElevatorState(Progress.IDLE, UP)), 1, 10, 20);

        Assert.assertFalse(controller.isMovingTowardsLevel(7));
    }

    @Test
    public void getSuitabilityScore() {
        TravelRequest request = new TravelRequest(8, 4, 10);
        int score_a = new ElevatorController(new Elevator("A", 0, 5, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20).getSuitabilityScore(request);
        int score_b = new ElevatorController(new Elevator("A", 0, 6, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20).getSuitabilityScore(request);
        System.out.println("a => " + score_a);
        System.out.println("b => " + score_b);

        Assert.assertTrue(score_b > score_a);
    }

    @Test
    public void getSuitabilityScore2() {
        TravelRequest request = new TravelRequest(7, 4, 10);

        int score_a = new ElevatorController(new Elevator("A", 0, 5, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20).getSuitabilityScore(request);
        int score_b = new ElevatorController(new Elevator("A", 0, 6, new ElevatorState(Progress.MOVING, DOWN)), 1, 10, 20).getSuitabilityScore(request);
        System.out.println("a => " + score_a);
        System.out.println("b => " + score_b);

        Assert.assertTrue(score_a > score_b);
    }

    @Test
    public void getSuitabilityScore3() {
        TravelRequest request = new TravelRequest(7, 4, 3);

        int score_a = new ElevatorController(new Elevator("A", 0, 5, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20).getSuitabilityScore(request);
        int score_b = new ElevatorController(new Elevator("A", 0, 6, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20).getSuitabilityScore(request);
        System.out.println("a => " + score_a);
        System.out.println("b => " + score_b);

        Assert.assertTrue(score_b > score_a);
    }

    @Test
    //If 'A' and 'B' are at the same level, but A is stopped and B is moving towards the request, and currentLevel of 'A', 'B', request
    //is same, The stopped elevator 'A' should given the priority, because 'B' is already started moving away from the level
    public void getSuitabilityScore4() {
        TravelRequest request = new TravelRequest(2, 4, 10);
        int score_a = new ElevatorController(new Elevator("A", 0, 1, new ElevatorState(Progress.IDLE, UP)), 1, 10, 20).getSuitabilityScore(request);
        int score_b = new ElevatorController(new Elevator("A", 0, 4, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20).getSuitabilityScore(request);
        System.out.println("a => " + score_a);
        System.out.println("b => " + score_b);

        Assert.assertTrue(score_a > score_b);
    }

    @Test
    //If 'A' and 'B' are at the same level, but A is stopped and B is moving opposite direction to the request, then priority should be
    //given to 'A'
    public void getSuitabilityScore5() {
        TravelRequest request = new TravelRequest(7, 4, 3);

        int score_a = new ElevatorController(new Elevator("A", 0, 9, new ElevatorState(Progress.IDLE, UP)), 1, 10, 20).getSuitabilityScore(request);
        int score_b = new ElevatorController(new Elevator("A", 0, 9, new ElevatorState(Progress.MOVING, UP)), 1, 10, 20).getSuitabilityScore(request);
        System.out.println("a => " + score_a);
        System.out.println("b => " + score_b);

        Assert.assertTrue(score_a > score_b);
    }

}
