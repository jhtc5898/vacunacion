package com.krugger.vacunacion.service.mapping;

import com.krugger.vacunacion.controller.entitiesControllers.EmployeeContr;
import com.krugger.vacunacion.entities.Employee;

public class EmployeeMapping {
    public static Employee mappingEmployee(EmployeeContr employeeContr) {
        Employee employee = new Employee(
                employeeContr.getIdentification_card(),
                employeeContr.getFirst_name(),
                employeeContr.getSecond_name(),
                employeeContr.getFirst_surname(),
                employeeContr.getSecond_surname(),
                employeeContr.getEmail());
        return employee;

    }
}
