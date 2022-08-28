package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PhoneRepository extends JpaRepository<Phone, Serializable> {
}
