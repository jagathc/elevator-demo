package com.springboot.elevator.elevatordemo.time;

import org.springframework.context.ApplicationEvent;

/**
 * @author jagath
 * This Enum class holds the elevator moving state
 */

public class ClockTick extends ApplicationEvent {
	
	private final long currentTime;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ClockTick(Object source, long currentTime) {
        super(source);
        this.currentTime = currentTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }
}
