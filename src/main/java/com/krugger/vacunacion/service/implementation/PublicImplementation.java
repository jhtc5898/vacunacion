package com.krugger.vacunacion.service.implementation;

import com.krugger.vacunacion.repository.TipeVaccineRepository;
import com.krugger.vacunacion.service.PublicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublicImplementation implements PublicService {
    @Autowired
    TipeVaccineRepository tipeVaccineRepository;

    /**
     * Present the types of vaccines publicly.
     *
     * @return
     */
    @Override
    public Object findAll() {
        if (tipeVaccineRepository.findAll() != null) {
            log.info(tipeVaccineRepository.findAll().toString());
            return tipeVaccineRepository.findAll();
        } else
            return ResponseEntity.badRequest().body("No info loaded");
    }

}
