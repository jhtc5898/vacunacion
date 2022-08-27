package com.krugger.vacunacion.service.implementation;

import com.krugger.vacunacion.controller.entitiesControllers.EmployeeContr;
import com.krugger.vacunacion.entities.Authenticator;
import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.exceptions.ErrorRequest;
import com.krugger.vacunacion.repository.AuthenticatorRepository;
import com.krugger.vacunacion.repository.EmployeeRepository;
import com.krugger.vacunacion.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.krugger.vacunacion.Utils.Constants.CODE_ERROR_INTERNAL;
import static com.krugger.vacunacion.Utils.Util.generateAutheticator;
import static com.krugger.vacunacion.service.mapping.EmployeeMapping.mappingEmployee;

@Slf4j
@Service
public class EmployeeImplementation implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AuthenticatorRepository authenticatorRepository;

    @Override
    public List<Employee> findAll() {
        return findAll();
    }

    @Override
    @Transactional
    public Object saveEmployee(EmployeeContr employeeContr) {
        try {
            Employee employee = mappingEmployee(employeeContr);
            log.info(employee.toString());
            employeeRepository.save(employee);
            Authenticator auth = generateAutheticator(employee);
            authenticatorRepository.save(auth);
            return auth;
        } catch (Exception e) {
            log.warn(employeeContr.toString());
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return errorRequest;
        }

    }


}
