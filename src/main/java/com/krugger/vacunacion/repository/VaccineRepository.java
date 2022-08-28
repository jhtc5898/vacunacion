package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface VaccineRepository extends JpaRepository<Vaccine, Serializable> {

    @Query(value = "select * from vacunacion.vac_tvaccine tvc where tvc.id_tipe_vaccine = :tipevaccine", nativeQuery = true)
    List<Vaccine> findAllByTipeVaccine(@Param("tipevaccine") UUID tipevaccine);

    @Query(value = "SELECT * FROM vacunacion.vac_tvaccine tvc WHERE tvc.date_vaccine BETWEEN :dateinit AND :datefin", nativeQuery = true)
    List<Vaccine> findDateEmployee(@Param("dateinit") Date dateinit, @Param("datefin") Date datefin);

}
