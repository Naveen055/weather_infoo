package com.example.weatherinfo.controller;

import com.example.weatherinfo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(@RequestParam String pincode, @RequestParam String forDate) {
        LocalDate date = LocalDate.parse(forDate);
        return weatherService.getWeatherInfo(pincode, date);
    }
}