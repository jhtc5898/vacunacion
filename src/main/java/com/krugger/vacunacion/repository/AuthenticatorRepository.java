package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Authenticator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface AuthenticatorRepository extends JpaRepository<Authenticator, Serializable> {

}
