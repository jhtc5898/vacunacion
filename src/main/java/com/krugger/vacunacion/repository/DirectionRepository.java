package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface DirectionRepository extends JpaRepository<Direction, Serializable> {

    @Query(value = "select * from vacunacion.vac_tdirection where id_employe =:idemployee", nativeQuery = true)
    List<Direction> findAllByIdemploye(@Param("idemployee") UUID idemployee);
}
