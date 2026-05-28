package com.ingestion_service.Ingestion_Service.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Async
    public void sendOverheatingAlert(String machineId, double temperature) {
        System.out.println("⚠️  [ALERT - OVERHEATING] Machine " + machineId + " is overheating! Current Temp: " + temperature + "°C");
    }

    @Async
    public void sendCriticalShutdownAlert(String machineId) {
        System.out.println("🚨 [CRITICAL ALERT - SHUTDOWN] Machine " + machineId + " has been SHUT DOWN suddenly!");
    }
}