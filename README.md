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

   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/weatherdb
   spring.datasource.username=postgres
   spring.datasource.password=yourpassword
   ```
3. **Add Your OpenWeather API Key**:
   - Open `src/main/java/com/example/weatherinfo/service/WeatherService.java`.
   - Replace `your_openweather_api_key` with your actual OpenWeather API key:
     
     ```java
     private static final String API_KEY = "your_openweather_api_key";
     ```

4. **Build the Project**:
   ```bash
   mvn clean install

---
   
## Running the Application

   1.**Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   2.**Access the Application**:
   
   The application will start on http://localhost:8080.

---

  ## API Endpoints
  **Get Weather Information**
  - URL: /weather   
  - Method: GET
  - Parameters:
      - pincode: The pincode for which weather information is required (e.g., 411014).
      - forDate: The date for which weather information is required (e.g., 2023-10-15).

  **Example Request**
  ```bash
   GET http://localhost:8080/weather?pincode=411014&forDate=2023-10-15
  ```
  **Example Response**
  ```bash
   {
  "coord": {
    "lon": 73.8553,
    "lat": 18.5196
  },
  "weather": [
    {
      "id": 800,
      "main": "Clear",
      "description": "clear sky",
      "icon": "01d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 303.15,
    "feels_like": 304.21,
    "temp_min": 303.15,
    "temp_max": 303.15,
    "pressure": 1013,
    "humidity": 54
  },
  "visibility": 10000,
  "wind": {
    "speed": 2.57,
    "deg": 360
  },
  "clouds": {
    "all": 0
  },
  "dt": 1697377200,
  "sys": {
    "type": 1,
    "id": 9052,
    "country": "IN",
    "sunrise": 1697331234,
    "sunset": 1697373456
  },
  "timezone": 19800,
  "id": 1259229,
  "name": "Pune",
  "cod": 200
}
```
## Database Schema

The application uses the following tables:

### `pincode_details`
| Column     | Type        | Description           |
|------------|-------------|-----------------------|
| pincode    | VARCHAR     | Primary key (pincode) |
| latitude   | DOUBLE      | Latitude of the pincode |
| longitude  | DOUBLE      | Longitude of the pincode |

### `weather_info`
| Column       | Type        | Description                     |
|--------------|-------------|---------------------------------|
| id           | BIGINT      | Primary key (auto-generated)    |
| pincode      | VARCHAR     | Foreign key (references pincode_details.pincode) |
| date         | DATE        | Date for which weather data is fetched |
| weather_data | TEXT        | Weather data in JSON format     |

## Testing the API
1.**Using Postman**:
   - Import the Postman collection (if available) or manually create a request.
   - Test the `/weather` endpoint with different pincodes and dates.

2.**Using cURL**:
```bash
   curl "http://localhost:8080/weather?pincode=411014&forDate=2023-10-15"
```

---

### License
This project is licensed under the MIT License. See the LICENSE file for details.

---

### Acknowledgments
- OpenWeather for providing the Geocoding and Weather APIs.

- Spring Boot for the backend framework.

- PostgreSQL for the database.

 ### contact
For any questions or feedback, feel free to reach out:

- Name: Naveen

- Email: spnaveenkumar623@gmail.com

- GitHub: Naveen055


