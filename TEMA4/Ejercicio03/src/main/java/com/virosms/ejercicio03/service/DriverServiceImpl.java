package com.virosms.ejercicio03.service;

import com.virosms.ejercicio03.model.Driver;
import com.virosms.ejercicio03.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> getDriverByCode(String code) {
        return driverRepository.findByCodeIgnoreCase(code);
    }

    @Override
    public void saveDriver(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void deleteDriverByCode(String code) {
        driverRepository.deleteByCode(code);
    }
}
