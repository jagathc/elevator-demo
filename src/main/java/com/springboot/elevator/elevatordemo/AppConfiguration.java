package com.springboot.elevator.elevatordemo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.elevator.elevatordemo.model.Elevator;
import com.springboot.elevator.elevatordemo.service.ElevatorController;

@Configuration
public class AppConfiguration {

    @Value("${elevator.count}")
    private int elevatorCount;

    @Value("${level.min}")
    private int minLevel;

    @Value("${level.max}")
    private int maxLevel;

    @Value("${elevator.passengers.max}")
    private int maxPassengers;


    @Bean(name = "elevatorControllers")
    public List<ElevatorController> getElevatorControllers() {
        return IntStream.range(0, elevatorCount)
                .mapToObj(i -> Character.toString((char) ('A' + i)))
                .map(name -> new ElevatorController(new Elevator(name, 1), minLevel, maxLevel, maxPassengers))
                .collect(Collectors.toList());
    }
}
