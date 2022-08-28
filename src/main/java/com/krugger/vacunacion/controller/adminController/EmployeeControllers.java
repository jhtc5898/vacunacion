package com.krugger.vacunacion.controller.adminController;


import com.krugger.vacunacion.controller.entitiesController.EmployeeContr;
import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.exceptions.ErrorRequest;
import com.krugger.vacunacion.service.EmployeeService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.krugger.vacunacion.Utils.Constants.CODE_ERROR_INTERNAL;
import static com.krugger.vacunacion.Utils.ValidationsRequest.general_validation;
import static com.krugger.vacunacion.exceptions.ErrorValidate.errorValidate;

@RestController
@RequestMapping("admin/")
@CrossOrigin(origins = "*")
public class EmployeeControllers {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/addEmployee")
    public ResponseEntity<Object> addEmployee(@Valid @ParameterObject @RequestBody EmployeeContr employeeContr) {
        try {
            if (general_validation(employeeContr) != false) {
                return ResponseEntity.badRequest().body(employeeService.saveEmployee(employeeContr));
            }
            return errorValidate(employeeContr);
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }


    @GetMapping(value = "/listEmployee")
    public List<Employee> listEmployee() {
        return employeeService.findAll();
    }


}
