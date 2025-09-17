# Diagrama de Arquitetura

```mermaid
graph TD
    User[Usuário] -->|HTTP| BFF[BFF]
    BFF -->|REST| Backend[Backend]
    Backend --> DB[(H2 Database)]
    Backend --> Cache[(Redis)]
    Backend --> MQ[(RabbitMQ)]
