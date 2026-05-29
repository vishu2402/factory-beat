# FactoryPulse

A real-time IoT telemetry pipeline built using Spring Boot, Apache Kafka, and PostgreSQL. The system simulates factory machine data, ingests telemetry events through REST APIs, processes them asynchronously using Kafka, stores machine logs in PostgreSQL, and generates alerts for critical conditions such as overheating and unexpected shutdowns.

## System Architecture

```text
┌────────────────────────────────┐
│   Factory Simulator (Python)   │
└───────────────┬────────────────┘
                │ (HTTP POST /api/v1/telemetry)
                ▼
┌────────────────────────────────┐
│ Ingestion API (Spring Boot)    │
└───────────────┬────────────────┘
                │ (KafkaTemplate.send)
                ▼
┌────────────────────────────────┐
│      Apache Kafka Broker       │
└───────────────┬────────────────┘
                │ (@KafkaListener)
                ▼
┌────────────────────────────────┐
│  Consumer Engine (Spring Boot) │
└───────┬────────────────┬───────┘
        │                │
        ▼                ▼
┌───────────────┐┌───────────────┐
│ PostgreSQL DB ││ Async Alerts  │
│    (Docker)   ││   Service     │
└───────────────┘└───────────────┘
```
