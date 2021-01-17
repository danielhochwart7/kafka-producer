package com.hochwart.kafkaproducer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Component
public class EventsGenerator {

    private final WebClient httpClient;
    private final Producer producer = new Producer();

    EventsGenerator(WebClient httpClient) {
        this.httpClient = httpClient;
    }

    @Scheduled(fixedRate = 1000)
    public void fetchEvents() {
        String event = httpClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
        producer.send(event);
        System.out.println(event);
    }
}
