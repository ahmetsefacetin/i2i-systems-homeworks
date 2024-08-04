package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class OperationMessageDeserializer implements Deserializer<OperationMessage> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OperationMessage deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, OperationMessage.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing OperationMessage", e);
        }
    }
}
