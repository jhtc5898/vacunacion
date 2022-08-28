package com.krugger.vacunacion.controller.employeeController;

import com.krugger.vacunacion.exceptions.ErrorRequest;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;
import com.krugger.vacunacion.service.EmployeeService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.krugger.vacunacion.Utils.Constants.CODE_ERROR_INTERNAL;
import static com.krugger.vacunacion.Utils.ValidationsRequest.validation_request;
import static com.krugger.vacunacion.exceptions.ErrorValidate.errorValidate;

@RestController
@RequestMapping("employee/")
@CrossOrigin(origins = "*")
public class UpdateEmployeeControllers {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/updateEmployee")
    public ResponseEntity<Object> updateEmployee(@Valid @ParameterObject @RequestBody UpdateEmployeePojo updateEmployeePojo) {
        try {
            if (validation_request(updateEmployeePojo) != false) {
                return ResponseEntity.badRequest().body(employeeService.updateEmployee(updateEmployeePojo));
            }
            return errorValidate(updateEmployeePojo);
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

}
