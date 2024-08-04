package org.example;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "operation-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.example.OperationMessageDeserializer");

        KafkaConsumer<String, OperationMessage> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("operation-topic"));

        while (true) {
            ConsumerRecords<String, OperationMessage> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, OperationMessage> record : records) {
                OperationMessage message = record.value();
                processOperation(message);
            }
        }
    }

    private static void processOperation(OperationMessage message) {
        int operand = message.getOperand();
        String operation = message.getOperation();

        switch (operation) {
            case "fibonacci":
                if (isFibonacci(operand)) {
                    System.out.println(operand + " is Fibonacci.");
                }
                else {
                    System.out.println(operand + " is not Fibonacci");
                }
                break;
            case "prime":
                if (isPrime(operand)) {
                    System.out.println(operand + " is Prime.");
                }
                else {
                    System.out.println(operand + " is not Prime");
                }
                break;
            case "fibPrime":
                if (isFibonacci(operand) && isPrime(operand)) {
                    System.out.println(operand + " is both Fibonacci and Prime.");
                }
                else if (isFibonacci(operand)) {
                    System.out.println(operand + " is Fibonacci but not Prime");
                }
                else if (isPrime(operand)) {
                    System.out.println(operand + " is Prime but not Fibonacci");
                }
                else {
                    System.out.println(operand + " is not Prime and not Fibonacci");
                }
                break;
            default:
                System.out.println("Invalid operation: " + operation);
        }
    }


    public static boolean isFibonacci(int operand) {
        int a = 0, b = 1;

        while (b < operand) {
            int c = a + b;
            a = b;
            b = c;
        }

        return b == operand;
    }

    private static boolean isPrime(int operand) {
        if (operand < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(operand); i++) {
            if (operand % i == 0) {
                return false;
            }
        }

        return true;
    }


}
