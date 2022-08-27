package com.krugger.vacunacion.service;


import com.krugger.vacunacion.controller.entitiesControllers.EmployeeContr;
import com.krugger.vacunacion.entities.Employee;

import java.util.List;


public interface EmployeeService {

    List<Employee> findAll();

    Object saveEmployee(EmployeeContr employeeContr);


}
