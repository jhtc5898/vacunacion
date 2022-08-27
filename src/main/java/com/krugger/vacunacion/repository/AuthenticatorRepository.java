package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Authenticator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface AuthenticatorRepository extends JpaRepository<Authenticator, Serializable> {

}
