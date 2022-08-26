package com.krugger.vacunacion.controllers;

import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("adm/")
@CrossOrigin(origins = "*")
public class AdmEmployeeControllers {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/listEmployee")
    public List<Employee> listEmployee(){
        return employeeService.findAll();
    }

    @GetMapping(value = "/saveEmployee")
    public Employee saveEmployee(){
        return employeeService.saveEmployee();
    }

}
