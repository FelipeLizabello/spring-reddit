# spring-auth-jwt

**Status:** In development

REST API for user authentication using Spring Boot and JWT.

## Technologies

- Java 25
- Spring Boot 4
- Spring Security
- Spring Data JPA
- MySQL
- JWT (jjwt 0.11.5)
- Lombok

## Features

- User registration with encrypted password (BCrypt)
- Input validation with Bean Validation
- JWT authentication (in progress)
- Protected routes with Spring Security

## Getting Started

### Prerequisites

- Java 25
- MySQL
- Maven

## Endpoints

| Method | URL | Description | Auth required |
|--------|-----|-------------|---------------|
| POST | `/api/auth/register` | Register a new user | No |
| POST | `/api/auth/login` | Login and get JWT token | No |


## Project Structure

```
src/main/java/com/example/demo/
├── config/
│   └── SecurityConfig.java
├── controller/
│   └── AuthController.java
├── dto/
│   └── UserDto.java
├── model/
│   └── User.java
├── repository/
│   └── UserRepository.java
└── service/
    └── AuthService.java
```

## Author

Felipe Fonseca Lizabello  
[LinkedIn](https://www.linkedin.com/in/felipe-lizabello-a2822230a) · [GitHub](https://github.com/FelipeLizabello)
