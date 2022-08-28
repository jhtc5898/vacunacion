package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RoleRepository extends JpaRepository<Role, Serializable> {
    Role findByNameAndStatus(String name, Boolean status);


}
