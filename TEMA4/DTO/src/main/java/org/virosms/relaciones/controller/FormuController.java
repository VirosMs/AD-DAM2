package org.virosms.relaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.virosms.relaciones.dto.Resume;
import org.virosms.relaciones.service.FormuService;

import java.util.List;

@Controller
@RequestMapping("/api/f1/formu")
public class FormuController {

    private final FormuService formuService;

    @Autowired
    public FormuController(FormuService formuService) {
        this.formuService = formuService;
    }

    @RequestMapping("/resume/{driverId}")
    public ResponseEntity<List<Resume>> getResumeByDriverId(@PathVariable Long driverId,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size
                                                      ) {

        return new ResponseEntity<>(formuService.getResumeByDriverId(driverId, page, size), HttpStatus.OK);
    }
}
