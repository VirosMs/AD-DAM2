package org.virosms.relaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.virosms.relaciones.model.Race;
import org.virosms.relaciones.repository.RaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    @Override
    public Optional<Race> getRaceByRound(int round) {
        return raceRepository.findByRound(round);
    }

    @Override
    public void saveDriver(Race race) {
        raceRepository.save(race);
    }

    @Override
    public void deleteRaceByRaceId(Long id) {
        raceRepository.deleteRaceByRaceId(id);
    }


}
