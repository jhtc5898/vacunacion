package com.krugger.vacunacion.service.implementation;

import com.krugger.vacunacion.entities.*;
import com.krugger.vacunacion.exceptions.ErrorRequest;
import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;
import com.krugger.vacunacion.repository.*;
import com.krugger.vacunacion.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.krugger.vacunacion.Utils.Constants.CODE_ERROR_INTERNAL;
import static com.krugger.vacunacion.Utils.Parameters.EMPLOYEE;
import static com.krugger.vacunacion.Utils.Util.generateAutheticator;
import static com.krugger.vacunacion.service.mapping.EmployeeMapping.mappingEmployee;

@Slf4j
@Service
public class EmployeeImplementation implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AuthenticatorRepository authenticatorRepository;
    @Autowired
    DirectionRepository directionRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Employee> findAll() {
        return findAll();
    }

    @Override
    @Transactional
    public Object saveEmployee(AddEmployeePojo employeePojo) {
        try {
            Employee employee = mappingEmployee(employeePojo);
            log.info(employee.toString());
            employeeRepository.save(employee);
            Role rol = roleRepository.findByNameAndStatus(EMPLOYEE, Boolean.TRUE);
            Authenticator auth = generateAutheticator(employee, rol);
            authenticatorRepository.save(auth);
            return auth;
        } catch (Exception e) {
            log.warn(employeePojo.toString());
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return errorRequest;
        }

    }

    @Override
    @Transactional
    public Object updateEmployee(UpdateEmployeePojo updateEmployeePojo) {
        try {
            Employee employee = updateInfoEmployee(updateEmployeePojo);
            Direction direction = saveDirection(updateEmployeePojo, employee);
            Phone phone = savePhone(updateEmployeePojo, employee);

            //Retornamos todos los campos guardados. En el caso del popup con esta informacion
            List<Object> listresp = new ArrayList<>();
            listresp.add(employee);
            listresp.add(direction);
            listresp.add(phone);
            return listresp;
        } catch (Exception e) {
            log.warn(updateEmployeePojo.toString());
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return errorRequest;
        }
    }

    public Employee updateInfoEmployee(UpdateEmployeePojo updateEmployeePojo) throws ParseException {
        Employee employee = employeeRepository.findByIdentificationCard(updateEmployeePojo.getIdentification_card());
        employee.setId(employee.getId());
        employee.setDate_birth(new SimpleDateFormat("dd/MM/yyyy").parse(updateEmployeePojo.getDate_birth()));
        employee.setStatus_vaccinated(updateEmployeePojo.getStatus_vaccine());
        employeeRepository.save(employee);
        return employee;

    }

    public Direction saveDirection(UpdateEmployeePojo updateEmployeePojo, Employee employee) {
        Direction direction = new Direction();
        direction.setId_employe(employee);
        direction.setPrimary_street(updateEmployeePojo.getDirection().getPrimary_street());
        direction.setSecondary_street(updateEmployeePojo.getDirection().getSecondary_street());
        direction.setPostal_code(updateEmployeePojo.getDirection().getPostal_code());
        direction.setCity(updateEmployeePojo.getDirection().getCity());
        direction.setProvince(updateEmployeePojo.getDirection().getProvince());
        direction.setCountry(updateEmployeePojo.getDirection().getCountry());
        direction.setStatus(updateEmployeePojo.getDirection().getStatus());
        directionRepository.save(direction);
        return direction;
    }

    public Phone savePhone(UpdateEmployeePojo updateEmployeePojo, Employee employee) {
        Phone phone = new Phone();
        phone.setId_employee(employee);
        phone.setPhone_priority(updateEmployeePojo.getPhone().getPhone_priority());
        phone.setPhone_priority_operator(updateEmployeePojo.getPhone().getPhone_priority_operator());
        phone.setPhone_home(updateEmployeePojo.getPhone().getPhone_home());
        phone.setStatus(updateEmployeePojo.getPhone().getStatus());
        phoneRepository.save(phone);
        return phone;
    }

}
