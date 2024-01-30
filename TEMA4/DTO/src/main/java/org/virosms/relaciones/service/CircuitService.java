package org.virosms.relaciones.service;

import org.virosms.relaciones.model.Circuit;

import java.util.List;
import java.util.Optional;

public interface CircuitService {
    List<Circuit> getAllCircuits();

    Optional<Circuit> getCircuitByCircuitref(String circuitref);

    void saveCircuit(Circuit circuit);
    void deleteCircuitByCircuitref(String circuitref);
}
