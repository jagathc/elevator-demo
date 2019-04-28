/**
 * 
 */
package com.springboot.elevator.elevatordemo.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.elevator.elevatordemo.model.Elevator;
import com.springboot.elevator.elevatordemo.model.TravelRequest;
import com.springboot.elevator.elevatordemo.service.ElevatorService;

/**
 * @author jagath
 * Rest Controller
 *
 */
@RestController
@RequestMapping("/elevator")
public class ElavatorRestController {

    @Autowired
    private ElevatorService elevatorService;

    @RequestMapping(value = "/travel", method = POST)
    public void travel(@RequestBody TravelRequest travelRequest) {
        elevatorService.addRequest(travelRequest);
    }

    @RequestMapping(value = "", method = GET)
    public List<Elevator> getElevatorStaus() {
        return elevatorService.getStatus();
    }

    @RequestMapping(value = "/{name}", method = GET)
    public Elevator getElevatorStaus(@PathVariable("name") String name) {
        return elevatorService.getStatus(name);
    }

}

