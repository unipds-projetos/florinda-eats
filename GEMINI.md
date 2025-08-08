# Project: Florinda Eats

## Project Overview

This project is a food delivery application for "Dona Florinda's restaurant," built as a microservices architecture. It consists of three main services:

*   **Pedidos (Orders):** Manages the menu and customer orders.
*   **Pagamentos (Payments):** Handles payment confirmations.
*   **Notas Fiscais (Invoices):** Generates XML invoices for orders.

The services are built with Java and Quarkus, using Maven for dependency management. The `pedidos` and `pagamentos` services use a MySQL database with Flyway for migrations and Panache for persistence. Asynchronous communication between services is handled by Kafka.

## Building and Running

### Prerequisites

*   Java
*   Maven
*   Docker

### Running the Application

1.  **Start Kafka:**
    Open a terminal in the project root and run:
    ```sh
    docker compose up
    ```

2.  **Run each microservice:**
    For each service (`pedidos`, `pagamentos`, `notas-fiscais`), navigate to its directory and run the following command:
    ```sh
    ./mvnw quarkus:dev
    ```

    Alternatively, you can run the services from your IDE using the Quarkus Maven plugin.

    *   **Pedidos:** Runs on port `8080` by default.
    *   **Pagamentos:** Runs on port `8081` by default.
    *   **Notas Fiscais:** Runs on port `8082` by default.

### Testing

To run the tests for each service, navigate to its directory and run:

```sh
./mvnw test
```

## Development Conventions

*   **Code Style:** The project follows standard Java conventions.
*   **Database Migrations:** Database changes are managed with Flyway. SQL migration scripts are located in the `src/main/resources/db/migration` directory of the `pedidos` and `pagamentos` services.
*   **API Documentation:** Each service includes a Postman collection for exploring its endpoints.
*   **Asynchronous Communication:** The `pedidos` service produces messages to a Kafka topic when an order is paid. The `notas-fiscais` service consumes these messages to generate invoices.
