package com.ingestion_service.Ingestion_Service.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "machines")
public class Machine {
    @Id
    private String id;
    private String status;
    private LocalDateTime lastUpdateTime;

    public Machine() {}
    public Machine(String id, String status, LocalDateTime lastUpdateTime) {
        this.id = id;
        this.status = status;
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getLastUpdateTime() { return lastUpdateTime; }
    public void setLastUpdateTime(LocalDateTime lastUpdateTime) { this.lastUpdateTime = lastUpdateTime; }
}