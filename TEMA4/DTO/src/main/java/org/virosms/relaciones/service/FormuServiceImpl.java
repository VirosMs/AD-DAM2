package org.virosms.relaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.virosms.relaciones.dto.ResultDTO;
import org.virosms.relaciones.dto.Resume;
import org.virosms.relaciones.mapper.FormuMapper;
import org.virosms.relaciones.model.Result;
import org.virosms.relaciones.repository.ResultRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormuServiceImpl implements FormuService {


    private final FormuMapper formuMapper;

    private final ResultRepository resultRepository;


    @Autowired
    public FormuServiceImpl(FormuMapper formuMapper, ResultRepository resultRepository) {
        this.formuMapper = formuMapper;
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Resume> getResumeByDriverId(Long driverId, int page, int size) {

        if(driverId == null)
            throw new IllegalArgumentException("Driver id is required");

        Pageable pageable = PageRequest.of(page, size);

        Page<Result> result = resultRepository.findByDriverId(driverId, pageable);



//        if(result == null || result.isEmpty() || result.getContent().isEmpty())
//            throw new IllegalArgumentException("Driver not found");
//
//        ResultDTO resultDTO = formuMapper.toResultDTO(result.getContent().get(0));
//
//        if(resultDTO == null)
//            throw new IllegalArgumentException("Result not found");
//
//        Resume resume = formuMapper.toResume(result.getContent().get(0).getRace(), result.getContent().get(0).getRace().getCircuit(), resultDTO);

        List<Resume> resume = new ArrayList<>();


        for(Result r : result.getContent()) {
            ResultDTO resultDTO = formuMapper.toResultDTO(r);
            resume.add(formuMapper.toResume(r.getRace(), r.getRace().getCircuit(), resultDTO));
        }

        if(resume.isEmpty())
            throw new IllegalArgumentException("Resume not found");

        return resume;
    }
}
