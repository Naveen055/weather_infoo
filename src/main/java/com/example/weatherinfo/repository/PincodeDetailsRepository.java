package com.example.weatherinfo.repository;

import com.example.weatherinfo.model.PincodeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PincodeDetailsRepository extends JpaRepository<PincodeDetails, String> {
}