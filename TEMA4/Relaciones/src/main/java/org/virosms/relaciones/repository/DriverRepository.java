package org.virosms.relaciones.repository;

import org.virosms.relaciones.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByCodeIgnoreCase(String code);

    void deleteByCode(String code);
    
}
