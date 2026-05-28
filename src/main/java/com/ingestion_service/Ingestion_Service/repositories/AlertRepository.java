package com.ingestion_service.Ingestion_Service.repositories;

import com.ingestion_service.Ingestion_Service.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByActiveTrue();
}