/**
 * 
 */
package com.springboot.elevator.elevatordemo.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.springboot.elevator.elevatordemo.model.Elevator;
import com.springboot.elevator.elevatordemo.model.TravelRequest;
import com.springboot.elevator.elevatordemo.persistance.model.ElevatorRequest;
import com.springboot.elevator.elevatordemo.persistance.model.ElevatorRequestRepository;
import com.springboot.elevator.elevatordemo.time.ClockTick;

/**
 * @author jagath
 * This is the elevator service class
 *
 */
@Component
public class ElevatorService implements ApplicationListener<ClockTick> {

    @Resource(name = "elevatorControllers")
    private List<ElevatorController> elevatorControllers;

    @Autowired
    private ElevatorRequestRepository elevatorRequestRepository;

    @Override
    public void onApplicationEvent(ClockTick event) {
        elevatorControllers.forEach(ElevatorController::updateElevatorState);
    }

    public void addRequest(final TravelRequest travelRequest) {
        Comparator<ElevatorController> comparator = (a, b) ->
                a.getSuitabilityScore(travelRequest).compareTo(b.getSuitabilityScore(travelRequest));
        ElevatorController controller = elevatorControllers.stream().max(comparator).get();

        controller.addRequest(travelRequest);

        elevatorRequestRepository.save(new ElevatorRequest(travelRequest.getLevel(),
                travelRequest.getDestination(), travelRequest.getPassengers(), controller.getElevatorName()));
    }

    public List<Elevator> getStatus() {
        return elevatorControllers.stream().map(ElevatorController::getStatus).collect(Collectors.toList());
    }

    public Elevator getStatus(String name) {
        return elevatorControllers.stream().filter(x -> x.getElevatorName().equals(name)).map(ElevatorController::getStatus).findFirst().get();
    }
}
