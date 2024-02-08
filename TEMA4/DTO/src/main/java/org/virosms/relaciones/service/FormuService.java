package org.virosms.relaciones.service;

import org.virosms.relaciones.dto.Resume;

import java.util.List;

public interface FormuService {
    List<Resume> getResumeByDriverId(Long driverId, int page, int size);
}
