package org.virosms.miprimeraapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.virosms.miprimeraapirest.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{
}
