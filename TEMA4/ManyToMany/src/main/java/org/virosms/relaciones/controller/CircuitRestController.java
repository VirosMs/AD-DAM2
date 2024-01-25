package org.virosms.relaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.virosms.relaciones.model.Circuit;
import org.virosms.relaciones.service.CircuitService;

import java.util.List;

@Controller
@RequestMapping("/api/f1")
public class CircuitRestController {

    private final CircuitService circuitService;

    @Autowired
    public CircuitRestController(CircuitService circuitService){
        this.circuitService = circuitService;
    }

    @GetMapping("/circuits")
    public ResponseEntity<List<Circuit>> getAllCircuits(){
        return ResponseEntity.ok(this.circuitService.getAllCircuits());
    }

    @GetMapping("/circuits/{circuitref}")
    public ResponseEntity<Circuit> getCircuitByRef(@PathVariable String circuitref){
        return this.circuitService.getCircuitByCircuitref(circuitref)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/circuits/create")
    public ResponseEntity<?> saveCircuit(@RequestBody Circuit circuit){
        if(circuit == null)
            return ResponseEntity.badRequest().build();

        circuitService.saveCircuit(circuit);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/circuits/remove/{circuitref}")
    public ResponseEntity<?> deleteCircuitByRef(String circuitref){
        if(circuitref == null || circuitref.isEmpty())
            return ResponseEntity.badRequest().build();

        circuitService.deleteCircuitByCircuitref(circuitref);
        return ResponseEntity.noContent().build();
    }

}
