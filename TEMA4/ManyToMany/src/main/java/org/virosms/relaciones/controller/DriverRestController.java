package org.virosms.relaciones.controller;

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
    public ResponseEntity<List<Driver>> getAll() {
        return ResponseEntity.ok(this.driverService.getAllDrivers());
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
