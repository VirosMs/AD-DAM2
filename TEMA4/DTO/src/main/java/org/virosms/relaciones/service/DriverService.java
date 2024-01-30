package org.virosms.relaciones.service;

import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.model.Driver;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<DriverDTO> getAllDrivers();


    Optional<Driver> getDriverByCode(String code);

    void saveDriver(Driver driver);

    void deleteDriverByCode(String code);

}
