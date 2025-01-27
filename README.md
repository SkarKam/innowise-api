# Simple CRUD API with Authentication

## Overview
This project is a simple CRUD API with user authentication, built using Spring Boot and PostgreSQL. The application and database are containerized and can be run using Docker Compose.

---

## Prerequisites
Ensure you have the following installed:
- **Java 22** or higher
- **Maven 3** or higher
- **Docker** and **Docker Compose**

---

## How to Set Up and Run
### Steps to Run the Application:
1. **Clone the repository:**
```bash
git clone https://github.com/SkarKam/innowise-api.git
```
2. **Navigate to the project directory:**
```bash
cd innowise-api
```
3. **Start the application with Docker Compose:**
```bash
docker-compose up --build
```
The application will run on http://localhost:8080.

## Testing the API.
To test the API, you can use tools like Postman or cURL. Below are the available endpoints and instructions:
1. **Sign up** (/register)
- Endpoint: http://localhost:8080/register
- Method: POST
- Request header:
```json
{
"Key": "Content-Type",
"Value": "application/json"
}
```
- Request body:
```json
{
    "username": "inn",
    "password": "inn"
}
```
- Response:
User registered successfully!

2. **Sign in**/login
- Endpoint: http://localhost:8080/login
- Method: POST
- Request header:
```json
{
"Key": "Content-Type",
"Value": "application/json"
}
```
- Request body:
```json
{
    "username": "inn",
    "password": "inn"
}
```
- Response:
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm4iLCJpYXQiOjE3Mzc5NzM4OTUsImV4cCI6MTczNzk3NDc5NX0.25KYwhddqsUnCKdR0Uv9Twkc9a-bySDXpD-8bKWgFtQ

3. **Get list of users** /users
- Endpoint: http://localhost:8080/users
- Method: GET
- Request header:
```json
{
"Key": "Content-Type",
"Value": "application/json"
}
```
- Authorization: Berear Token
- Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm4iLCJpYXQiOjE3Mzc5NzM4OTUsImV4cCI6MTczNzk3NDc5NX0.25KYwhddqsUnCKdR0Uv9Twkc9a-bySDXpD-8bKWgFtQ
- Request body: empty
- Response:
```json
[
    {
        "id": 1,
        "username": "inn",
        "password": "$2a$10$xipcC6fZJGR1jmdG8lsE8.CldhJkfcJkiXGnIVpn3QUOtmWiFmJw."
    },
    {
        "id": 2,
        "username": "skarK",
        "password": "$2a$10$rNfXsDNr0N5nKgz8Dm35wO.rqtg48xIIJqDk6xDQ6hEpe3Un4r8Au"
    }
]
```
4. **Delete a user** /users/{id}
- Endpoint: http://localhost:8080/users/{id}
- Method: DELETE
- Request header:
```json
{
"Key": "Content-Type",
"Value": "application/json"
}
```
- Authorization: Berear Token
- Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm4iLCJpYXQiOjE3Mzc5NzM4OTUsImV4cCI6MTczNzk3NDc5NX0.25KYwhddqsUnCKdR0Uv9Twkc9a-bySDXpD-8bKWgFtQ
- Request body: empty
- Response:
204 No Content

5. **Change user credentials** /users/{id}
- Endpoint: http://localhost:8080/users/{id}
- Method: PUT
- Request header:
```json
{
"Key": "Content-Type",
"Value": "application/json"
}
```
- Authorization: Berear Token
- Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbm4iLCJpYXQiOjE3Mzc5NzM4OTUsImV4cCI6MTczNzk3NDc5NX0.25KYwhddqsUnCKdR0Uv9Twkc9a-bySDXpD-8bKWgFtQ
- Request body:
```json
{
    "username": "inn",
    "password": "inn"
}
```
- Response:
User was updated: Account(id=3, username=test2, password=test2)

## How to configure the environment variables.
The main configuration file is application.properties, located in the src/main/resources directory.
### Database configuration
To configure the database connection, modify the following lines:
```properties
spring.datasource.url=jdbc:postgresql://db:5432/innowise_db
spring.datasource.username=temp
spring.datasource.password=temp
spring.datasource.driver-class-name=org.postgresql.Driver
```
### Hibernate configuration
For Hibernate settings, adjust:
```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### JWT configuration
Modify the JWT settings as needed
```properties
security.jwt.secret-key=SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJVFadQssw5c
security.jwt.expiration-time=900000
```

## Notes
- Technlogoy used:
  - Java 22 with Spring Boot and Spring Security
  - JWT for authetnication
  - PostgreSQL
  - Docker
- To stop the appliaction:
```bash
docker-compose down
```
