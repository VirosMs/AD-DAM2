package org.virosms.relaciones.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.virosms.relaciones.dto.DriverDTO;
import org.virosms.relaciones.dto.DriverDetail;
import org.virosms.relaciones.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByCodeIgnoreCase(String code);

    void deleteByCode(String code);

    //@Query("SELECT d.driverId as id, d.code as code, d.forename as forename, d.surname as surname, d.constructor.constructorId as constructorId, d.constructor.constructorRef as constructorRef, d.constructor.name as name FROM Driver d")
    Page<DriverDetail> findAllProjectedBy(Pageable pageable);
}
