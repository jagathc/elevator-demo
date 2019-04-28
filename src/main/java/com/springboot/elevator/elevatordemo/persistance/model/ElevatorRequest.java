package com.springboot.elevator.elevatordemo.persistance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author jagath
 * This is the elivator request entity for persist
 *
 */

@Entity
public class ElevatorRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer fromLevel;
    private Integer toLevel;
    private Integer passengers;
    private String elevator;
    private Long createdTime;

    public ElevatorRequest(Integer fromLevel, Integer toLevel, Integer passengers, String elevator) {
        this.fromLevel = fromLevel;
        this.toLevel = toLevel;
        this.passengers = passengers;
        this.elevator = elevator;
        this.createdTime = System.currentTimeMillis();
    }

    public ElevatorRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFromLevel() {
        return fromLevel;
    }

    public void setFromLevel(Integer fromLevel) {
        this.fromLevel = fromLevel;
    }

    public Integer getToLevel() {
        return toLevel;
    }

    public void setToLevel(Integer toLevel) {
        this.toLevel = toLevel;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public String getElevator() {
        return elevator;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
