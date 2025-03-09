package com.example.weatherinfo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pincode_details")
public class PincodeDetails {
    @Id
    @Column(name = "pincode")
    private String pincode;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}