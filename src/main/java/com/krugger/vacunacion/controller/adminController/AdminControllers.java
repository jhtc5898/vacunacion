package com.krugger.vacunacion.controller.adminController;


import com.krugger.vacunacion.exceptions.ErrorRequest;
import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;
import com.krugger.vacunacion.pojo.admin.EditInformationPojo;
import com.krugger.vacunacion.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.krugger.vacunacion.Utils.Constants.CODE_ERROR_INTERNAL;
import static com.krugger.vacunacion.Utils.Constants.NOT_DATE;
import static com.krugger.vacunacion.Utils.ValidationsRequest.validateDate;
import static com.krugger.vacunacion.Utils.ValidationsRequest.validation_request;
import static com.krugger.vacunacion.exceptions.ErrorValidate.errorValidate;

@RestController
@RequestMapping("admin/")
@CrossOrigin(origins = "*")
public class AdminControllers {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Creating new employees rol:ADMIN
     *
     * @param employeePojo
     * @return
     */
    @Operation(summary = "Creating new employees ",
            responses = {
                    @ApiResponse(responseCode = "601", description = "Validate Identification"),
                    @ApiResponse(responseCode = "602", description = "Validate Mail"),
                    @ApiResponse(responseCode = "603", description = "Validate Name"),
                    @ApiResponse(responseCode = "604", description = "Validate Date Format (dd/mm/yyyy)")
            })

    @PostMapping(value = "/addEmployee")
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody AddEmployeePojo employeePojo) {
        try {
            if (validation_request(employeePojo) != false) {
                return ResponseEntity.badRequest().body(employeeService.saveEmployee(employeePojo));
            }
            return errorValidate(employeePojo);
        } catch (NullPointerException n) {
            return errorValidate(employeePojo);
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

    /**
     * Edit information employee
     *
     * @return
     */
    @Operation(summary = "Edit information basic employee",
            responses = {
                    @ApiResponse(responseCode = "600", description = "Exepcion Internal")
            })

    @PostMapping(value = "/editInformation")
    public ResponseEntity<Object> editInformation(@Valid @RequestBody EditInformationPojo editInformationPojo) {
        try {
            return ResponseEntity.ok().body(employeeService.editInformationEmployee(editInformationPojo));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

    /**
     * Eliminated logic of the employee by identification card
     *
     * @param identification
     * @return
     */
    @Operation(summary = "Eliminated logic of the employee by identification card",
            responses = {
                    @ApiResponse(responseCode = "600", description = "Exepcion Internal")})
    @DeleteMapping(value = "/deleteEmployee")
    public ResponseEntity<Object> deleteEmployee(@RequestParam("identification") String identification) {
        try {
            return ResponseEntity.ok().body(employeeService.deleteLogicEmployee(identification));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }


    /**
     * List of employees by state
     *
     * @return
     */
    @Operation(summary = "List of employees by state",
            responses = {
                    @ApiResponse(responseCode = "600", description = "Exepcion Internal")
            })

    @GetMapping(value = "/listEmployee")
    public Object listEmployee() {
        return ResponseEntity.ok().body(employeeService.findAllByStatus());
    }


    /**
     * Get employees by state
     *
     * @param status
     * @return
     */
    @Operation(summary = "Get employees by state ",
            responses = {
                    @ApiResponse(responseCode = "600", description = "Exepcion Internal")})
    @GetMapping(value = "/employeeVaccinatedStatus")
    public ResponseEntity<Object> employeeVaccinatedStatus(@RequestParam("status") Boolean status) {
        try {
            return ResponseEntity.badRequest().body(employeeService.getVaccineEmployee(status));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

    /**
     * Get employees by type of covid vaccine
     *
     * @param name
     * @return
     */
    @Operation(summary = "Get employees by type of covid vaccine",
            responses = {
                    @ApiResponse(responseCode = "600", description = "Exepcion Internal")})
    @GetMapping(value = "/employeeVaccinatedType")
    public ResponseEntity<Object> employeeVaccinatedType(@RequestParam("nameVaccine") String name) {
        try {
            return ResponseEntity.badRequest().body(employeeService.getNameVaccineEmployee(name));
        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }

    /**
     * Get employees by vaccination date
     *
     * @param dateinit
     * @param datefin
     * @return
     */
    @Operation(summary = "Get employees by vaccination date",
            responses = {
                    @ApiResponse(responseCode = "600", description = "Exepcion Internal")})
    @GetMapping(value = "/employeeVaccinatedDate")
    public ResponseEntity<Object> employeeVaccinatedDate(@RequestParam("dateinit") String dateinit, @RequestParam("datefin") String datefin) {
        try {
            if (validateDate(dateinit) && validateDate(dateinit)) {
                return ResponseEntity.ok().body(employeeService.getDateVaccineEmployee(dateinit, datefin));
            }
            return ResponseEntity.badRequest().body(NOT_DATE);

        } catch (Exception e) {
            ErrorRequest errorRequest = new ErrorRequest(e.getCause().getCause().getMessage(), CODE_ERROR_INTERNAL, e.getCause());
            return ResponseEntity.internalServerError().body(errorRequest);
        }
    }


}
