/**
 * 
 */
package com.springboot.elevator.elevatordemo.time;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author jagath
 *
 */

@Component
public class Clock implements ApplicationEventPublisherAware {
    @Value("${clock.tick}")
    private int clockTick;

    private Timer timer = new Timer();
    private ApplicationEventPublisher applicationEventPublisher;
    private AtomicLong time = new AtomicLong();

    private TimerTask task = new TimerTask() {

        @Override
        public void run() {
            applicationEventPublisher.publishEvent(new ClockTick(Clock.this, time.incrementAndGet()));
        }
    };

    @PostConstruct
    public void init() {
        timer.schedule(task, 0, clockTick);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
