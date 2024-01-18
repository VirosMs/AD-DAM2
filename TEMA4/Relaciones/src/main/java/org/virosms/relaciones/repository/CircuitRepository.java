package org.virosms.relaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virosms.relaciones.model.Circuit;

import java.util.Optional;

public interface CircuitRepository extends JpaRepository<Circuit, Long> {

    Optional<Circuit> findByCircuitRefIgnoreCase(String circuitRef);

    void deleteByCircuitRef(String circuitRef);

}
