# Weather Info API

This is a Spring Boot application that provides weather information for a given pincode and date. It uses the OpenWeather Geocoding API to fetch latitude and longitude for a pincode and the OpenWeather Weather API to fetch weather data.

---

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Prerequisites](#prerequisites)
4. [Setup and Installation](#setup-and-installation)
5. [Running the Application](#running-the-application)
6. [API Endpoints](#api-endpoints)
7. [Testing the API](#testing-the-api)
8. [Database Schema](#database-schema)
9. [Troubleshooting](#troubleshooting)
10. [License](#license)

---

## Features

- Fetch weather information for a given pincode and date.
- Store pincode details (latitude, longitude) and weather information in a PostgreSQL database.
- Optimize API calls by caching data in the database.

---

## Technologies Used

- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL
- **APIs**: OpenWeather Geocoding API, OpenWeather Weather API
- **Build Tool**: Maven
- **Testing**: Postman

---

## Prerequisites

Before running the application, ensure you have the following installed:

1. **Java Development Kit (JDK)**: Version 17 or higher.
2. **PostgreSQL**: Installed and running.
3. **Maven**: For building the project.
4. **OpenWeather API Key**: Sign up at [OpenWeather](https://openweathermap.org/api) and get your API key.

---

## Setup and Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/weather-info-api.git
   cd weather-info-api

2. **Set Up the Database:**

Create a PostgreSQL database named weatherdb.
Update the database connection details in src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/weatherdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword