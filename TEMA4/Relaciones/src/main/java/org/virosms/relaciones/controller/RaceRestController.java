package org.virosms.relaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.virosms.relaciones.model.Race;
import org.virosms.relaciones.service.RaceService;

import java.util.List;

@Controller
@RequestMapping("/api/f1")
public class RaceRestController {

    private final RaceService raceService;


    @Autowired
    public RaceRestController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/races/")
    public ResponseEntity<List<Race>> getAllRaces(){
        return ResponseEntity.ok(this.raceService.getAllRaces());
    }

    @GetMapping("/races/{round}")
    public ResponseEntity<Race> getRaceByRound(@PathVariable int round){
        return this.raceService.getRaceByRound(round)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/races/create")
    public ResponseEntity<?> saveRace(@RequestBody Race race){
        if (race == null)
            return ResponseEntity.badRequest().build();


        this.raceService.saveDriver(race);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/races/remove/{id}")
    public ResponseEntity<?> deleteRaceByRaceId(@PathVariable Long id){
        if(id == null)
            return ResponseEntity.badRequest().build();

        this.raceService.deleteRaceByRaceId(id);
        return ResponseEntity.noContent().build();
    }
}
