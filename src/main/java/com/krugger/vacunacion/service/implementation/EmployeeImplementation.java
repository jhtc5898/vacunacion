package com.krugger.vacunacion.service.implementation;

import com.krugger.vacunacion.entities.*;
import com.krugger.vacunacion.exceptions.ErrorRequest;
import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;
import com.krugger.vacunacion.pojo.admin.EditInformationPojo;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;
import com.krugger.vacunacion.pojo.employee.VaccineEmployeePojo;
import com.krugger.vacunacion.repository.*;
import com.krugger.vacunacion.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.krugger.vacunacion.Utils.Constants.*;
import static com.krugger.vacunacion.Utils.Parameters.EMPLOYEE;
import static com.krugger.vacunacion.Utils.Util.generateAutheticator;
import static com.krugger.vacunacion.Utils.Util.parseDate;
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
    @Autowired
    TipeVaccineRepository tipeVaccineRepository;
    @Autowired
    VaccineRepository vaccineRepository;

    /**
     * Get all employees by active status
     *
     * @return
     */
    @Override
    public Object findAllByStatus() {
        if (!employeeRepository.findAllByStatus(Boolean.TRUE).isEmpty()) {
            log.info(employeeRepository.findAllByStatus(Boolean.TRUE).toString());
            return employeeRepository.findAllByStatus(Boolean.TRUE);
        }
        return ResponseEntity.ok().body("Sin Informacion");

    }

    /**
     * Get employee information by ID number
     *
     * @param identification
     * @return
     */
    public Object informationEmployee(String identification) {
        List<Object> resp = new ArrayList<>();
        if (employeeRepository.findByIdentificationCard(identification) != null) {
            Employee empl = employeeRepository.findByIdentificationCard(identification);
            resp.add(empl);
            if (!directionRepository.findAllByIdemploye(empl.getId()).isEmpty()) {
                resp.add(directionRepository.findAllByIdemploye(empl.getId()));
            } else {
                resp.add("Sin Direcciones Agregadas");
            }

            if (!phoneRepository.findAllByIdemploye(empl.getId()).isEmpty()) {
                List<Phone> lisPhone = phoneRepository.findAllByIdemploye(empl.getId());
                resp.add(lisPhone);
            } else {
                resp.add("Sin Phones Agregados");
            }
            log.info(resp.toString());
            return resp;
        }
        return ResponseEntity.ok().body("Sin Informacion");
    }

    /**
     * Logical elimination of an employee goes to false state
     *
     * @param identification
     * @return
     */
    @Transactional
    public Object deleteLogicEmployee(String identification) {
        if (employeeRepository.findByIdentificationCard(identification) != null) {
            Employee employee = employeeRepository.findByIdentificationCard(identification);
            employee.setStatus(Boolean.FALSE);
            employeeRepository.save(employee);
            log.info(employee.toString());
            return employee;
        }
        return ResponseEntity.ok().body(NOT_EMPLOYEE);

    }

    /**
     * Edit basic information of the employee configurable from the POJO
     *
     * @param editInformationPojo
     * @return
     */
    @Transactional
    public Object editInformationEmployee(EditInformationPojo editInformationPojo) {
        //Buscamos el empleado
        if (employeeRepository.findByIdentificationCard(editInformationPojo.getIdentification_card()) != null) {
            Employee employee = employeeRepository.findByIdentificationCard(editInformationPojo.getIdentification_card());
            employee.setId(employee.getId());
            employee.setEmail(editInformationPojo.getEmail());
            employee.setStatusvaccinated(editInformationPojo.getStatusVaccine());
            employeeRepository.save(employee);
            log.info(employee.toString());
            return employee;
        }
        return ResponseEntity.ok().body(NOT_EMPLOYEE);

    }

    /**
     * Save new employee with the POJO defined by the challenge
     *
     * @param employeePojo
     * @return
     */
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
            log.info(employee.toString());
            return auth;
        } catch (Exception e) {
            log.warn(employeePojo.toString());
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return errorRequest;
        }

    }

    /**
     * Update of employee information a pojo to save addresses and telephone numbers
     *
     * @param updateEmployeePojo
     * @return
     */
    @Override
    @Transactional
    public Object updateEmployee(UpdateEmployeePojo updateEmployeePojo) {
        try {
            Employee employee = updateInfoEmployee(updateEmployeePojo);
            Direction direction = saveDirection(updateEmployeePojo, employee);
            Phone phone = savePhone(updateEmployeePojo, employee);
            String respVaccine = messageVaccine(updateEmployeePojo);

            //Retornamos todos los campos guardados. En el caso del popup con esta informacion
            List<Object> listresp = new ArrayList<>();
            listresp.add(respVaccine);
            listresp.add(employee);
            listresp.add(direction);
            listresp.add(phone);

            log.info(listresp.toString());
            return listresp;
        } catch (Exception e) {
            log.warn(updateEmployeePojo.toString());
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return errorRequest;
        }
    }

    /**
     * Aggregation of employee vaccinations, search for the employee by ID.
     *
     * @param vaccineEmployeePojo
     * @return
     */
    @Transactional
    public Object addVaccineEmployee(VaccineEmployeePojo vaccineEmployeePojo) {
        try {
            if (employeeRepository.findByIdentificationCard(vaccineEmployeePojo.getIdentification_card()) != null) {
                Employee employee = employeeRepository.findByIdentificationCard(vaccineEmployeePojo.getIdentification_card());
                if (tipeVaccineRepository.findByNamevaccine(vaccineEmployeePojo.getNameVaccine()) != null) {
                    TipeVaccine tipeVaccine = tipeVaccineRepository.findByNamevaccine(vaccineEmployeePojo.getNameVaccine());
                    Vaccine vaccine = new Vaccine();
                    vaccine.setEmployee(employee);
                    vaccine.setTipeVaccine(tipeVaccine);
                    vaccine.setDate_vaccine(new SimpleDateFormat("dd/MM/yyyy").parse(vaccineEmployeePojo.getDateVaccine()));
                    vaccine.setNumber_doses(vaccineEmployeePojo.getDosis());
                    vaccineRepository.save(vaccine);
                    log.info(vaccine.toString());
                    return vaccine;
                }
                return ResponseEntity.badRequest().body("Correctly enter the type of vaccine");

            }
            return ResponseEntity.badRequest().body("No Related Employee Found");
        } catch (Exception e) {
            log.warn(vaccineEmployeePojo.toString());
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return errorRequest;
        }
    }

    /**
     * Obtain all employees who are vaccinated by the state
     *
     * @param status
     * @return
     */
    public Object getVaccineEmployee(Boolean status) {
        if (!employeeRepository.findAllByStatusAndStatusvaccinated(Boolean.TRUE, status).isEmpty()) {
            return employeeRepository.findAllByStatusAndStatusvaccinated(Boolean.TRUE, status);
        }
        return NOT_INFORMATION;
    }

    /**
     * Get all employees vaccinated by type of vaccine.Enter a name
     *
     * @param name
     * @return
     */
    public Object getNameVaccineEmployee(String name) {
        if (tipeVaccineRepository.findByNamevaccine(name) != null) {
            TipeVaccine tipe = tipeVaccineRepository.findByNamevaccine(name);
            if (!vaccineRepository.findAllByTipeVaccine(tipe.getId()).isEmpty()) {
                List<Vaccine> listvac = vaccineRepository.findAllByTipeVaccine(tipe.getId());
                log.info(listvac.toString());
                return listvac;
            } else {
                return ResponseEntity.badRequest().body(NOT_INFORMATION);
            }
        } else {
            return ResponseEntity.badRequest().body(NOT_TYPE);
        }


    }

    /**
     * Obtener los vacunados por fecha de inicio y fin
     *
     * @param dateinit
     * @param datefin
     * @return
     * @throws ParseException
     */
    public Object getDateVaccineEmployee(String dateinit, String datefin) throws ParseException {

        Date init = parseDate(dateinit);
        Date fin = parseDate(datefin);
        if (!vaccineRepository.findDateEmployee(init, fin).isEmpty()) {
            return vaccineRepository.findDateEmployee(init, fin);
        }
        return ResponseEntity.badRequest().body(NOT_DATE_REQUEST);

    }

    /**
     * Update of employee information defined by the pojo
     *
     * @param updateEmployeePojo
     * @return
     * @throws ParseException
     */
    @Transactional
    public Employee updateInfoEmployee(UpdateEmployeePojo updateEmployeePojo) throws ParseException {
        Employee employee = employeeRepository.findByIdentificationCard(updateEmployeePojo.getIdentification_card());
        employee.setId(employee.getId());
        employee.setDate_birth(new SimpleDateFormat("dd/MM/yyyy").parse(updateEmployeePojo.getDate_birth()));
        employee.setStatusvaccinated(updateEmployeePojo.getStatus_vaccine());
        employeeRepository.save(employee);
        return employee;

    }


    public Direction saveDirection(UpdateEmployeePojo updateEmployeePojo, Employee employee) {
        Direction direction = new Direction();
        direction.setIdemploye(employee);
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
        phone.setIdemployee(employee);
        phone.setPhone_priority(updateEmployeePojo.getPhone().getPhone_priority());
        phone.setPhone_priority_operator(updateEmployeePojo.getPhone().getPhone_priority_operator());
        phone.setPhone_home(updateEmployeePojo.getPhone().getPhone_home());
        phone.setStatus(updateEmployeePojo.getPhone().getStatus());
        phoneRepository.save(phone);
        return phone;
    }

    public String messageVaccine(UpdateEmployeePojo updateEmployeePojo) {
        if (updateEmployeePojo.getStatus_vaccine() == Boolean.TRUE) {
            return MESSAGE_VACCINE;
        }
        return MESSAGE_NOT_VACCINE;

    }
}
