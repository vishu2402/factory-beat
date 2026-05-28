package com.ingestion_service.Ingestion_Service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingestion_service.Ingestion_Service.models.TelemetryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TelemetryController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String TOPIC = "machine-telemetry-topic";

    @PostMapping("/telemetry")
    public ResponseEntity<String> receiveTelemetry(@RequestBody TelemetryData data) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(data);

            kafkaTemplate.send(TOPIC, data.getMachineId(), jsonPayload);

            System.out.println("Sent to Kafka: " + jsonPayload);
            return ResponseEntity.ok("Data successfully sent to Kafka topic!");

        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Error parsing JSON");
        }
    }
}