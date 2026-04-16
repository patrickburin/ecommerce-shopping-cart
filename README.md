# Basket Service

Simple portfolio project built with Spring Boot to simulate a shopping basket API.

The application allows you to:

- fetch products from an external API
- create and update a shopping basket
- register the basket payment
- persist basket data in MongoDB
- cache product queries with Redis

## Tech Stack

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data MongoDB
- Spring Data Redis
- Spring Cache
- Spring Cloud OpenFeign
- Swagger / OpenAPI
- Maven
- Docker Compose

## Main Approaches

- `OpenFeign` is used to consume the external products API
- `MongoDB` stores basket data
- `Redis` is used as cache for product queries
- `ControllerAdvice` centralizes error handling
- `Swagger UI` documents the available endpoints

## Running Locally

### Requirements

- Java 17
- Docker and Docker Compose

### 1. Start infrastructure

```bash
docker compose up -d
```

This will start:

- MongoDB on `localhost:27017`
- Redis on `localhost:6379`

### 2. Run the application

```bash
./mvnw spring-boot:run
```

The API will start on:

`http://localhost:8080`

## API Documentation

Swagger UI:

`http://localhost:8080/swagger-ui.html`

OpenAPI JSON:

`http://localhost:8080/v3/api-docs`

## Useful Endpoints

- `GET /products` - list products from the external API
- `GET /products/{id}` - get one product by id
- `POST /basket` - create a basket
- `GET /basket/{id}` - get a basket by id
- `PUT /basket/{id}` - update basket products
- `PUT /basket/{id}/payment` - register basket payment
- `DELETE /basket/{id}` - delete a basket

## Quick Notes

- Product data comes from the external Platzi API configured in `application.yml`
- MongoDB and Redis need to be running for the project to work correctly
- This is a simple study/portfolio project, so the focus here is clarity and practical backend concepts
