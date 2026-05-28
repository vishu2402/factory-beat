package com.ingestion_service.Ingestion_Service.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingestion_service.Ingestion_Service.models.Alert;
import com.ingestion_service.Ingestion_Service.models.Machine;
import com.ingestion_service.Ingestion_Service.models.TelemetryLog;
import com.ingestion_service.Ingestion_Service.repositories.AlertRepository;
import com.ingestion_service.Ingestion_Service.repositories.MachineRepository;
import com.ingestion_service.Ingestion_Service.repositories.TelemetryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TelemetryConsumer {

    @Autowired private MachineRepository machineRepository;
    @Autowired private TelemetryLogRepository logRepository;
    @Autowired private AlertRepository alertRepository;
    @Autowired private ObjectMapper objectMapper;

    @KafkaListener(topics = "machine-telemetry-topic", groupId = "factory-group")
    public void consume(String message) {
        try {
            var data = objectMapper.readTree(message);
            String machineId = data.get("machineId").asText();
            String eventType = data.get("eventType").asText();
            double value = data.get("value").asDouble();
            long timestamp = data.get("timestamp").asLong();

            String calculatedStatus = "RUNNING";
            if ("SHUTDOWN".equals(eventType)) calculatedStatus = "STOPPED";
            else if (value > 100.0) calculatedStatus = "OVERHEATING";

            Machine machine = machineRepository.findById(machineId)
                    .orElse(new Machine(machineId, calculatedStatus, LocalDateTime.now()));
            machine.setStatus(calculatedStatus);
            machine.setLastUpdateTime(LocalDateTime.now());
            machineRepository.save(machine);

            TelemetryLog log = new TelemetryLog(machine, eventType, value, timestamp);
            logRepository.save(log);

            if (value > 100.0) {
                Alert alert = new Alert(machine, "WARNING", "Machine is overheating at " + value + "°C", LocalDateTime.now(), true);
                alertRepository.save(alert);
            }
            if ("SHUTDOWN".equals(eventType)) {
                Alert alert = new Alert(machine, "CRITICAL", "Machine shutdown unexpectedly!", LocalDateTime.now(), true);
                alertRepository.save(alert);
            }

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}