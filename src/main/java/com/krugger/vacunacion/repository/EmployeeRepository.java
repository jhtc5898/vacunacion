package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {
    List<Employee> findAll();
}
