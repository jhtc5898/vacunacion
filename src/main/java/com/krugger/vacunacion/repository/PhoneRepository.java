package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, Serializable> {
    @Query(value = "select * from vacunacion.vac_tphone where id_employee =:idemployee", nativeQuery = true)
    List<Phone> findAllByIdemploye(@Param("idemployee") UUID idemployee);
}
