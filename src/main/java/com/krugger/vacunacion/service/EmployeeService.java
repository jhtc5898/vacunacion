package com.krugger.vacunacion.service;


import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;
import com.krugger.vacunacion.pojo.employee.VaccineEmployeePojo;

import java.text.ParseException;
import java.util.List;


public interface EmployeeService {

    List<Employee> findAll();

    Object saveEmployee(AddEmployeePojo employeePojo);

    Object updateEmployee(UpdateEmployeePojo updateEmployeePojo);

    Object addVaccineEmployee(VaccineEmployeePojo vaccineEmployeePojo);

    Object getVaccineEmployee(Boolean status);

    Object getNameVaccineEmployee(String name);

    Object getDateVaccineEmployee(String dateinit, String datefin) throws ParseException;

}
