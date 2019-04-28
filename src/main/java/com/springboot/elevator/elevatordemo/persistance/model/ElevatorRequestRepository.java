package com.springboot.elevator.elevatordemo.persistance.model;

import org.springframework.data.repository.CrudRepository;

/**
 * @author jagath
 * This is the elivator request repository for persist
 *
 */

public interface ElevatorRequestRepository extends CrudRepository<ElevatorRequest, Long> { }
