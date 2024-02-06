package org.virosms.relaciones.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.dto.DriverDetail;
import org.virosms.relaciones.model.Driver;

import org.virosms.relaciones.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverRestController {

    private final DriverService driverService;

    @Autowired
    public DriverRestController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers/{code}")
    public ResponseEntity<Driver> getByCode(@PathVariable String code) {
        return this.driverService.getDriverByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/drivers/")
    public ResponseEntity<List<DriverDTO>> getAll() {
        return ResponseEntity.ok(this.driverService.getAllDrivers());
    }

    @GetMapping("/driversDTO/")
    public ResponseEntity<List<DriverDetail>> getAllDTO(@RequestParam(defaultValue = "0") int pageKey,
                                          @RequestParam(defaultValue = "10") int pageSize,
                                          @RequestParam(defaultValue = "driverId") String sortBy,
                                          @RequestParam(defaultValue = "asc") String sortDirect) {

        Page<DriverDetail> driverDTOPage = this.driverService.getAllDriversResponse(pageKey, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(driverDTOPage.getContent(), HttpStatus.OK);
    }

    @PutMapping("/drivers")
    public ResponseEntity<Driver> update(@RequestBody Driver driver) {
        if(driver.getDriverId() == null) {
            return ResponseEntity.badRequest().build();
        }

        this.driverService.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }

    @PostMapping("/drivers")
    public ResponseEntity<Driver> create(@RequestBody Driver driver) {
        if(driver == null || driver.getDriverId() != null) {
            return ResponseEntity.badRequest().build();
        }

        this.driverService.saveDriver(driver);
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping("/drivers/{code}")
    public ResponseEntity<Driver> delete(@PathVariable String code) {
        if(code == null) {
            return ResponseEntity.badRequest().build();
        }

        this.driverService.deleteDriverByCode(code);
        return ResponseEntity.noContent().build();
    }
}
