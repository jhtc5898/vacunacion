package com.krugger.vacunacion.repository;

import com.krugger.vacunacion.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Serializable> {
    List<Employee> findAll();
    Employee findByIdentificationCard(String identification);
    List<Employee> findAllByStatusAndStatusvaccinated(Boolean status,Boolean statusVaccinated);
}
