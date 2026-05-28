package com.ingestion_service.Ingestion_Service.models;

import jakarta.persistence.*;

@Entity
@Table(name = "telemetry_logs")
public class TelemetryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    private String eventType;
    private double value;
    private long timestamp;

    public TelemetryLog() {}
    public TelemetryLog(Machine machine, String eventType, double value, long timestamp) {
        this.machine = machine;
        this.eventType = eventType;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Long getId() { return id; }
    public Machine getMachine() { return machine; }
    public void setMachine(Machine machine) { this.machine = machine; }
    public String getEventType() { return eventType; }
    public double getValue() { return value; }
    public long getTimestamp() { return timestamp; }
}