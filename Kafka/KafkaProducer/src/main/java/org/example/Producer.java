package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.example.OperationMessageSerializer");

        KafkaProducer<String, OperationMessage> producer = new KafkaProducer<>(props);
        OperationMessage message = new OperationMessage(13, "fibPrime");
        producer.send(new ProducerRecord<>("operation-topic", "operation", message));

        producer.close();
    }
}
