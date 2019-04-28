/**
 * 
 */
package com.springboot.elevator.elevatordemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import com.springboot.elevator.elevatordemo.model.Direction;
import com.springboot.elevator.elevatordemo.model.Elevator;
import com.springboot.elevator.elevatordemo.model.ElevatorState;
import com.springboot.elevator.elevatordemo.model.Progress;
import com.springboot.elevator.elevatordemo.model.TravelRequest;

/**
 * @author jagatj
 *
 */
public class ElevatorController {

    private Elevator elevator;
    private final Integer maxLevel;
    private final Integer minLevel;
    private final Integer passengerLimitPerElevator;

    private List<TravelRequest> pendingRequests = new ArrayList<>();

    private List<TravelRequest> acceptedRequests = new ArrayList<>();

    public ElevatorController(Elevator elevator, int minLevel, int maxLevel, int passengerLimitPerElevator) {
        this.elevator = elevator;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.passengerLimitPerElevator = passengerLimitPerElevator;
    }

    public Elevator updateElevatorState() {
        pendingRequests.stream().forEach(this::processRequest);
        pendingRequests.clear();

        elevator = updateElevator(elevator);
        return elevator;
    }

    public Elevator getStatus() {
        return elevator;
    }

    public String getElevatorName() {
        return elevator.getName();
    }

    boolean isMovingTowardsLevel(int level) {
        return elevator.getProgress() == Progress.MOVING &&
                ((elevator.getDirection() == Direction.UP && elevator.getCurrentLevel() < level) ||
                        (elevator.getDirection() == Direction.DOWN && elevator.getCurrentLevel() > level));
    }

    private Integer getLevels() {
        return maxLevel - minLevel + 1;
    }

    /**
     * calculate the suitability score of this elevator moving to service the provided request
     * @param request
     * @return
     */
    public Integer getSuitabilityScore(TravelRequest request) {
        if (elevator.getPassengers() >= passengerLimitPerElevator) {
            return Integer.MIN_VALUE;
        }

        int levelDiff = Math.abs(request.getLevel() - elevator.getCurrentLevel());
        Integer levels = getLevels();
        switch (elevator.getProgress()) {
            case IDLE:
                return levels - levelDiff + 1;
            case MOVING:
                if (isMovingTowardsLevel(request.getLevel())) {
                    if (elevator.getDirection() == request.getDirection()) {
                        //the elevator is moving towards the required level and it is moving in the same direction as the travel request
                        return levels - levelDiff + 1;
                    } else {
                        //the elevator is moving towards the required level but it is moving in the opposite direction to the travel request
                        return levels - levelDiff;
                    }
                } else {
                    return 1;
                }
            default:
                return Integer.MIN_VALUE;
        }
    }

    /**
     * process the pending requests and assign it to the elevator to take action.
     * @param newRequest
     */
    private void processRequest(TravelRequest newRequest) {
        int insertIndex = 0;
        int size = acceptedRequests.size();
        for (int i = 0; i < size; i++) {
            TravelRequest request = acceptedRequests.get(i);
            if (request.getDirection() != newRequest.getDirection()) {
                insertIndex++;
                continue;
            }

            if ((newRequest.getDirection() == Direction.UP && newRequest.getLevel() < request.getLevel()) ||
                    (newRequest.getDirection() == Direction.DOWN && newRequest.getLevel() > request.getLevel())) {
                insertIndex = i;
            }
        }
        acceptedRequests.add(insertIndex, newRequest);
    }

    /**
     * move the elevator up or down to the next level, acccoding to its moving direction.
     * If the elevator is in IDLE mode, keep it in the same level
     * @param elevator
     * @return the new level of the elevator
     */
    private Integer getNewLevel(final Elevator elevator) {
        int level = elevator.getCurrentLevel();
        if (elevator.getProgress() == Progress.MOVING) {
            switch (elevator.getDirection()) {
                case UP:
                    if (level != maxLevel) {
                        return level + 1;
                    }
                    break;
                case DOWN:
                    if (level != minLevel) {
                        return level - 1;
                    }
                    break;
            }
        }

        return level;
    }

    /**
     * determine the elevator's direction of movement and movement state (MOVING or IDLE) according the the currently servicing
     * requests and current state of elevator
     * @param state : current movement state of elevator
     * @param level : current level of elevator
     * @return: new state of elevator
     */
    private ElevatorState getNewElevatorState(final ElevatorState state, final Integer level) {
        Direction direction = state.getDirection();
        Progress progress;
        if ((direction == Direction.UP && level == maxLevel) || (direction == Direction.DOWN && level == minLevel)) {
            progress = Progress.IDLE;
        } else {
            progress = state.getProgress();
        }

        if (acceptedRequests.size() > 0) {
            TravelRequest first = acceptedRequests.get(0);
            Direction requestDirection = first.getDirection();

            if (progress == Progress.IDLE) {

                if (level > first.getLevel()) {
                    return new ElevatorState(Progress.MOVING, Direction.DOWN);
                } else if (level < first.getLevel()) {
                    return new ElevatorState(Progress.MOVING, Direction.UP);
                } else {
                    return new ElevatorState(Progress.MOVING, requestDirection);
                }
            } else {
                if (level == first.getLevel() && direction != requestDirection) {
                    return new ElevatorState(Progress.MOVING, requestDirection);
                }
            }
        } else {
            return new ElevatorState(Progress.IDLE, direction);
        }

        return new ElevatorState(progress, direction);
    }

    /**
     * This method should be called on each clock tick, to move the elevator and service the requests.
     * in each call,
     *  1) determine the direction of movement
     *  2) move to the next level if required (UP or DOWN)
     *  3) take passengers onboard
     *  4) allow passengers to get off the elevator
     *  5) update the current servicing requests if required.
     * @param elevator
     * @return: the new Elevator instance
     */
    public Elevator updateElevator(final Elevator elevator) {
        if (acceptedRequests.size() > 0) {
            final Integer newLevel = getNewLevel(elevator);
            final ElevatorState newState = getNewElevatorState(elevator.getState(), newLevel);

            TravelRequest first = acceptedRequests.get(0);
            BinaryOperator<Integer> sum = (a, b) -> a + b;

            List<TravelRequest> requestsInThisLevel = acceptedRequests.stream()
                    .filter(x -> x.getDirection() == first.getDirection() && x.getLevel() == newLevel)
                    .collect(Collectors.toList());

            int passengerToTravel = 0;
            for (TravelRequest request : requestsInThisLevel) {
                int remainingSpace = passengerLimitPerElevator - elevator.getPassengers() - passengerToTravel;
                if (remainingSpace == 0) {
                    break;
                }

                Integer remainingPassengers = request.getRemainingPassengers();
                if (remainingPassengers > 0) {
                    if (remainingPassengers <= remainingSpace) {
                        passengerToTravel += remainingPassengers;
                        request.setPassengersInElevator(remainingPassengers);
                    } else {
                        passengerToTravel += remainingSpace;
                        request.setPassengersInElevator(remainingSpace);
                    }
                }
            }

            if (passengerToTravel > 0) {
                return new Elevator(elevator.getName(), elevator.getPassengers() + passengerToTravel,
                        newLevel, getNewElevatorState(newState, newLevel));
            } else {
                int passengersLeaving = acceptedRequests.stream()
                        .filter(x -> x.getDirection() == first.getDirection() && x.getDestination() == newLevel)
                        .map(TravelRequest::getPassengersInElevator).reduce(0, sum);
                if (passengersLeaving > 0 && elevator.getPassengers() >= passengersLeaving) {
                    acceptedRequests.removeAll(acceptedRequests.stream()
                            .filter(x -> x.getDirection() == first.getDirection() && x.getDestination() == newLevel)
                            .collect(Collectors.toList()));

                    return new Elevator(elevator.getName(), elevator.getPassengers() - passengersLeaving,
                            newLevel, getNewElevatorState(newState, newLevel));
                }
            }

            return new Elevator(elevator.getName(), elevator.getPassengers(), newLevel, newState);
        }

        return elevator;
    }

    public void addRequest(TravelRequest travelRequest) {
        pendingRequests.add(travelRequest);
    }
}
