package org.virosms.relaciones.service;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.dto.DriverDetail;
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
        return driverRepository.findAll()
                .stream()
                .map(driver -> mapper.fromDriverAndConstructorToDriverDTO(driver, driver.getConstructor()))
                .toList();
    }

    @Override
    public Page<DriverDetail> getAllDriversResponse(int pageKey, int pageSize, String sortBy, String sortDirect) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirect), sortBy);
        Pageable pageable = PageRequest.of(pageKey, pageSize, sort);
        return driverRepository.findAllProjectedBy(pageable);
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
