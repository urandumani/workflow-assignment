# Workflow assignment

---

## Prerequisites

- Docker installed: [Get Docker](https://docs.docker.com/get-docker/)
- Docker Compose installed (comes bundled with recent Docker versions)

---

## Project Structure

- `docker-compose.yml` - defines MySQL and Spring Boot app containers
- `Dockerfile` -  builds Spring Boot app image
- `src/` - Spring Boot source code
- `target/` - Maven build output (JAR file)

---

## How to Run

### 1. Build your Spring Boot app JAR

```
./mvnw clean package
```

### 2. Run docker compose
#### This will spin up one docker container for mysql and one for the spring app. Flyway will populate the database.
```
docker-compose up
```

### 3. Navigate to http://localhost:8080/swagger-ui/index.html

### 4. Authenticate via `/api/authenticate` endpoint as an administrator:

```
{
    "usernameOrEmail": "uran",
    "password": "password"
}
```

### 5. Use the generated token to authorize within swagger (top right Authorize green button)

```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cmFuIiwiaWF0IjoxNzU0NjE5MDcxLCJleHAiOjE3NTQ3MDU0NzF9.esTQD-CyPj65rSHE2EANzucfwh4eDw8H6rpGIvatyz8"
}
```

### 6. You're now free to test any of the other backend endpoints

---

#### note: you can also login as limited role user (Category Manager) which does not have writing permissions

```angular2html
{
    "usernameOrEmail": "nuredin",
    "password": "password"
}
```
