package com.virosms.ejercicio03.service;

import com.virosms.ejercicio03.model.Driver;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<Driver> getAllDrivers();

    Optional<Driver> getDriverByCode(String code);

    void saveDriver(Driver driver);

    void deleteDriverByCode(String code);

}
