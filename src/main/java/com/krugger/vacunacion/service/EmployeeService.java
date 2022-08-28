package com.krugger.vacunacion.service;


import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;
import com.krugger.vacunacion.pojo.admin.EditInformationPojo;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;
import com.krugger.vacunacion.pojo.employee.VaccineEmployeePojo;

import java.text.ParseException;


public interface EmployeeService {


    Object findAllByStatus();

    Object informationEmployee(String identification);

    Object editInformationEmployee(EditInformationPojo editInformationPojo);

    Object deleteLogicEmployee(String identification);

    Object saveEmployee(AddEmployeePojo employeePojo);

    Object updateEmployee(UpdateEmployeePojo updateEmployeePojo);

    Object addVaccineEmployee(VaccineEmployeePojo vaccineEmployeePojo);

    Object getVaccineEmployee(Boolean status);

    Object getNameVaccineEmployee(String name);

    Object getDateVaccineEmployee(String dateinit, String datefin) throws ParseException;

}
