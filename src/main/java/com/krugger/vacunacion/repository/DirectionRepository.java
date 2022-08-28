package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DirectionRepository extends JpaRepository<Direction, Serializable> {

}
