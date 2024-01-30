package org.virosms.relaciones.service;

import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.mapper.FormuMapper;
import org.virosms.relaciones.model.Driver;
import org.virosms.relaciones.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    private final FormuMapper mapper;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, FormuMapper mapper) {
        this.driverRepository = driverRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll().stream().map(mapper::driverToDriverDTO).toList();
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
