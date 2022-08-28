package com.krugger.vacunacion.service.mapping;

import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;

public class EmployeeMapping {
    public static Employee mappingEmployee(AddEmployeePojo employeePojo) {
        Employee employee = new Employee(
                employeePojo.getIdentification_card(),
                employeePojo.getFirst_name(),
                employeePojo.getSecond_name(),
                employeePojo.getFirst_surname(),
                employeePojo.getSecond_surname(),
                employeePojo.getEmail());
        return employee;

    }
}
