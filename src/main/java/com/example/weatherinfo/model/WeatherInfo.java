package com.example.weatherinfo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "weather_info")
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "weather_data", columnDefinition = "TEXT")
    private String weatherData;
}