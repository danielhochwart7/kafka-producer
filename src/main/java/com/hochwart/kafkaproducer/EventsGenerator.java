package com.hochwart.kafkaproducer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@Component
public class EventsGenerator {

    private WebClient httpClient;
    private List<String> events = new ArrayList<>();

    EventsGenerator(WebClient httpClient) {
        this.httpClient = httpClient;
    }

    @Scheduled(fixedRate = 1000)
    public void fetchEvents() {
        String event = httpClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
        events.add(event);
    }

    public List<String> getEvents() {
        return events;
    }
}
