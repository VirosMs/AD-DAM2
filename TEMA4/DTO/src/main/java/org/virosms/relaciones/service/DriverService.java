package org.virosms.relaciones.service;

import org.springframework.data.domain.Page;
import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.dto.DriverDetail;
import org.virosms.relaciones.model.Driver;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<DriverDTO> getAllDrivers();

    Page<DriverDetail> getAllDriversResponse(int pageKey, int pageSize, String sortBy, String sortDirect);

    Optional<Driver> getDriverByCode(String code);

    void saveDriver(Driver driver);

    void deleteDriverByCode(String code);

}
