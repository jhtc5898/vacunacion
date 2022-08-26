package com.krugger.vacunacion.service.implementation;

import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.repository.EmployeeRepository;
import com.krugger.vacunacion.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeImplementation implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return findAll();
    }

    @Override
    public Employee saveEmployee() {
        Employee employee = new Employee();
        employee.setDate_birth(new Date());
        employee.setEmail("john");
        employee.setFirst_name("1");
        employee.setSecond_name("2");
        employee.setFirst_surname("asd");
        employee.setSecond_surname("asdss");
        employee.setIdentification_card("10000");
        employee.setStatus(Boolean.TRUE);
        employee.setStatus_vaccinated(Boolean.TRUE);
        employee.setVaccinated_descripction("Vacunado");
        employeeRepository.save(employee);

        return employee;
    }

}
