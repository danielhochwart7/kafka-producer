package com.hochwart.kafkaproducer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class EventsGenerator {

    @Scheduled(fixedRate = 1000)
    public void fetchEvents() {
        System.out.println("Scheduler running...");
    }
}
