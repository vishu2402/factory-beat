package com.ingestion_service.Ingestion_Service.models;

import jakarta.persistence.*;

@Entity
@Table(name = "machine_telemetry")
public class TelemetryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String machineId;
    private String eventType;
    private double value;
    private long timestamp;

    public TelemetryEntity() {}

    public TelemetryEntity(String machineId, String eventType, double value, long timestamp) {
        this.machineId = machineId;
        this.eventType = eventType;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public String getMachineId() { return machineId; }
    public String getEventType() { return eventType; }
    public double getValue() { return value; }
    public long getTimestamp() { return timestamp; }
}