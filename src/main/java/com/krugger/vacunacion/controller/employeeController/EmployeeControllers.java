package com.krugger.vacunacion.controller.employeeController;

import com.krugger.vacunacion.exceptions.ErrorRequest;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;
import com.krugger.vacunacion.pojo.employee.VaccineEmployeePojo;
import com.krugger.vacunacion.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class EmployeeControllers {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Get employee information
     *
     * @param identification
     * @return
     */
    @Operation(summary = "Get employee information",
            responses = {
                    @ApiResponse(responseCode = "600", description = "Exepcion Internal")})
    @GetMapping(value = "/informationEmployee")
    public ResponseEntity<Object> informationEmployee(@RequestParam("identification") String identification) {
        try {
            return ResponseEntity.badRequest().body(employeeService.informationEmployee(identification));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }


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

    @PostMapping(value = "/addEmployeeVaccine")
    public ResponseEntity<Object> addEmployeeVaccine(@Valid @ParameterObject @RequestBody VaccineEmployeePojo vaccineEmployeePojo) {
        try {
            if (validation_request(vaccineEmployeePojo) != false) {
                return ResponseEntity.badRequest().body(employeeService.addVaccineEmployee(vaccineEmployeePojo));
            }
            return null;
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }


}
