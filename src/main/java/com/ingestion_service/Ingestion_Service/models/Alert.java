package com.ingestion_service.Ingestion_Service.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    private String alertType;
    private String message;
    private LocalDateTime createdAt;
    private boolean active;

    public Alert() {}
    public Alert(Machine machine, String alertType, String message, LocalDateTime createdAt, boolean active) {
        this.machine = machine;
        this.alertType = alertType;
        this.message = message;
        this.createdAt = createdAt;
        this.active = active;
    }

    public Long getId() { return id; }
    public Machine getMachine() { return machine; }
    public String getAlertType() { return alertType; }
    public String getMessage() { return message; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}