package com.krugger.vacunacion.service;


import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;

import java.util.List;


public interface EmployeeService {

    List<Employee> findAll();

    Object saveEmployee(AddEmployeePojo employeePojo);

    Object updateEmployee(UpdateEmployeePojo updateEmployeePojo);


}
