package org.virosms.relaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virosms.relaciones.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
