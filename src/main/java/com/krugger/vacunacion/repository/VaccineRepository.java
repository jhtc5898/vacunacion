package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface VaccineRepository extends JpaRepository<Vaccine, Serializable> {
}
