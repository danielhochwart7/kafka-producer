package com.hochwart.kafkaproducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;

public class Producer {

    private final KafkaProducer<Long, String> kafkaProducer;

    public Producer() {
        Properties config = new Properties();
        config.put("bootstrap.servers", "0.0.0.0:9092");
        config.put("key.serializer","org.apache.kafka.common.serialization.LongSerializer");
        config.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        config.put("acks", "all");
        this.kafkaProducer = new KafkaProducer<>(config);
    }

    public void send(String event) {
        kafkaProducer.send(new ProducerRecord<>("topic-hochwart", UUID.randomUUID().getLeastSignificantBits(), event));
    }
}
