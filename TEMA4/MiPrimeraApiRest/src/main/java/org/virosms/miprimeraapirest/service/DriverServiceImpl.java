package org.virosms.miprimeraapirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.virosms.miprimeraapirest.model.Driver;
import org.virosms.miprimeraapirest.repository.DriverRepository;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
}
