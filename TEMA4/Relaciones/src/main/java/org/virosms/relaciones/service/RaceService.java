package org.virosms.relaciones.service;

import org.virosms.relaciones.model.Race;

import java.util.List;
import java.util.Optional;

public interface RaceService {
    List<Race> getAllRaces();

    Optional<Race> getRaceByRound(int round);

    void saveDriver(Race race);

    void deleteRaceByRaceId(int id);


}
