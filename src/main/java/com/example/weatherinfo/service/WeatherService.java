package com.example.weatherinfo.service;

import com.example.weatherinfo.model.PincodeDetails;
import com.example.weatherinfo.model.WeatherInfo;
import com.example.weatherinfo.repository.PincodeDetailsRepository;
import com.example.weatherinfo.repository.WeatherInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    private PincodeDetailsRepository pincodeDetailsRepository;

    @Autowired
    private WeatherInfoRepository weatherInfoRepository;

    private static final String GEOCODING_API_URL = "http://api.openweathermap.org/geo/1.0/zip?zip={pincode},IN&appid={apiKey}";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}";
    private static final String API_KEY = "df52720958f1dbdaeb5dd91c4b3983df"; // Replace with your actual API key

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public String getWeatherInfo(String pincode, LocalDate date) {
        try {
            logger.info("Fetching weather info for pincode: {} and date: {}", pincode, date);

            // Check if weather info is already in the database
            Optional<WeatherInfo> weatherInfoOptional = weatherInfoRepository.findByPincodeAndDate(pincode, date);
            if (weatherInfoOptional.isPresent()) {
                logger.info("Weather info found in database for pincode: {} and date: {}", pincode, date);
                return weatherInfoOptional.get().getWeatherData();
            }

            // Fetch latitude and longitude
            PincodeDetails pincodeDetails = pincodeDetailsRepository.findById(pincode).orElseGet(() -> {
                logger.info("Fetching geocoding data for pincode: {}", pincode);
                Map<String, String> uriVariables = new HashMap<>();
                uriVariables.put("pincode", pincode);
                uriVariables.put("apiKey", API_KEY);

                RestTemplate restTemplate = new RestTemplate();
                try {
                    PincodeDetails newPincodeDetails = restTemplate.getForObject(GEOCODING_API_URL, PincodeDetails.class, uriVariables);
                    if (newPincodeDetails == null) {
                        logger.error("No geocoding data found for pincode: {}", pincode);
                        throw new RuntimeException("No geocoding data found for pincode: " + pincode);
                    }
                    logger.info("Saving geocoding data for pincode: {}", pincode);
                    pincodeDetailsRepository.save(newPincodeDetails);
                    return newPincodeDetails;
                } catch (HttpClientErrorException e) {
                    logger.error("Failed to fetch geocoding data for pincode: {}. Error: {}", pincode, e.getMessage());
                    throw new RuntimeException("Failed to fetch geocoding data: " + e.getMessage());
                }
            });

            if (pincodeDetails == null) {
                logger.error("No geocoding data available for pincode: {}", pincode);
                throw new RuntimeException("No geocoding data available for pincode: " + pincode);
            }

            // Fetch weather data
            logger.info("Fetching weather data for latitude: {} and longitude: {}", pincodeDetails.getLatitude(), pincodeDetails.getLongitude());
            Map<String, String> weatherUriVariables = new HashMap<>();
            weatherUriVariables.put("lat", pincodeDetails.getLatitude().toString());
            weatherUriVariables.put("lon", pincodeDetails.getLongitude().toString());
            weatherUriVariables.put("apiKey", API_KEY);

            RestTemplate restTemplate = new RestTemplate();
            try {
                String weatherData = restTemplate.getForObject(WEATHER_API_URL, String.class, weatherUriVariables);
                if (weatherData == null) {
                    logger.error("No weather data found for pincode: {}", pincode);
                    throw new RuntimeException("No weather data found for pincode: " + pincode);
                }

                // Save weather info to database
                logger.info("Saving weather data for pincode: {} and date: {}", pincode, date);
                WeatherInfo weatherInfo = new WeatherInfo();
                weatherInfo.setPincode(pincode);
                weatherInfo.setDate(date);
                weatherInfo.setWeatherData(weatherData);
                weatherInfoRepository.save(weatherInfo);

                return weatherData;
            } catch (HttpClientErrorException e) {
                logger.error("Failed to fetch weather data for pincode: {}. Error: {}", pincode, e.getMessage());
                throw new RuntimeException("Failed to fetch weather data: " + e.getMessage());
            }
        } catch (RuntimeException e) {
            logger.error("Error fetching weather info for pincode: {}. Error: {}", pincode, e.getMessage());
            throw new RuntimeException("Error fetching weather info: " + e.getMessage());
        }
    }
}