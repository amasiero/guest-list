# Guest List Management System

This is a guest list management system built with the following technologies:

- Java 21;
- Spring Boot 3.2;
- PostgreSQL 16;
- Gradle 8;
- Docker.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes.

### Prerequisites

- Java 21
- Gradle
- Docker
- Docker Compose

### Installing

A step by step series of examples that tell you how to get a development environment running.

1. Clone the repository

```bash
git clone https://github.com/amasiero/guest-list.git
```

2. Navigate to the project directory

```bash
cd guest-list
```

3. Run

```bash
docker-compose up --build
```

The application will be available at `http://localhost:8080`.

## Running the tests

To run the tests, execute the following command in the project directory:

```bash
./gradlew test
```

## Built With Gradle

This project uses Gradle as a build tool. To build the project, execute the following command in the project directory:

```bash
./gradlew bootRun
```

## Endpoints available

The endpoints available are:

- GET `/api/v1/guest_list` - This endpoint is used to list all guests.
- POST `/api/v1/guest_list/{name}` - This endpoint is used to create a reservation.
- DELETE `/api/v1/guests/{name}` - This endpoint is used to remove a guest.
- GET `/api/v1/guests` - This endpoint is used to list all arrived guests.
- PUT `/api/v1/guests/{name}` - This endpoint is used to update a reservation.
- GET `/api/v1/seats_empty` - This endpoint is used to retrieve the number of empty seats.

## Authors

- **Andrey Masiero** - [github](github.com/amasiero)
