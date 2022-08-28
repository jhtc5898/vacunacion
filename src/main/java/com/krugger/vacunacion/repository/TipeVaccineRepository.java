package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.TipeVaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface TipeVaccineRepository extends JpaRepository<TipeVaccine, Serializable> {
    TipeVaccine findByNamevaccine(String name);
}
