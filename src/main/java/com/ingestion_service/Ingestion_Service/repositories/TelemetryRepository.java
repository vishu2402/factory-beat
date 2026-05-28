package com.ingestion_service.Ingestion_Service.repositories;

import com.ingestion_service.Ingestion_Service.models.TelemetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryEntity, Long> {
}