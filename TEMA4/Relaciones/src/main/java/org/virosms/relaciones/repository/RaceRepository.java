package org.virosms.relaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virosms.relaciones.model.Race;

import java.util.Optional;

public interface RaceRepository extends JpaRepository<Race, Long> {

    Optional<Race> findByRound(int round);

    void deleteRaceByRaceId(Long raceId);


}
