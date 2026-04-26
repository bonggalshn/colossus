# Colossus Engine

Java 25 Spring Boot application with Thymeleaf frontend.

## Build

```bash
./mvnw clean package
```

## Run

```bash
./mvnw spring-boot:run
```

## API Endpoints

- `GET /api/v1/hello` - Hello World endpoint
- `GET /` - Home page

## Technology Stack

- Java 25
- Spring Boot 3.x
- Thymeleaf
- PostgreSQL

## Structure

```
src/main/java/id/colossus/
├── controller/    # REST controllers
├── facade/        # Facade layer
├── biz-service/   # Business logic
├── core-service/  # Core services
├── repository/    # Data access
├── integration/  # External integrations
├── common-util/   # Utilities
└── common-lang/   # Constants, enums, DTOs
```