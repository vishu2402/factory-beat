package com.ingestion_service.Ingestion_Service.controllers;

import com.ingestion_service.Ingestion_Service.models.Alert;
import com.ingestion_service.Ingestion_Service.models.Machine;
import com.ingestion_service.Ingestion_Service.repositories.AlertRepository;
import com.ingestion_service.Ingestion_Service.repositories.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AnalyticsController {

    @Autowired private MachineRepository machineRepository;
    @Autowired private AlertRepository alertRepository;

    @GetMapping("/machines/{id}/status")
    public ResponseEntity<Machine> getMachineStatus(@PathVariable String id) {
        return machineRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/alerts/active")
    public ResponseEntity<List<Alert>> getActiveAlerts() {
        List<Alert> activeAlerts = alertRepository.findByActiveTrue();
        return ResponseEntity.ok(activeAlerts);
    }
}