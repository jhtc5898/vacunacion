package com.krugger.vacunacion.service;


import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.repository.EmployeeRepository;

import java.util.List;


public interface EmployeeService {

     List<Employee> findAll();

     Employee saveEmployee();


}
