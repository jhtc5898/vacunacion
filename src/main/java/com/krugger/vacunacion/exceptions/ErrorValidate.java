package com.krugger.vacunacion.exceptions;

import com.krugger.vacunacion.controller.entitiesControllers.EmployeeContr;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.krugger.vacunacion.Utils.Constants.*;
import static com.krugger.vacunacion.Utils.ValidationsRequest.*;

public class ErrorValidate {
    public static ResponseEntity<Object> errorValidate(EmployeeContr employeeContr) {
        List<ErrorRequest> listErrorRequest = new ArrayList<>();

        if (!validateName(employeeContr.getFirst_name() +
                employeeContr.getSecond_name() +
                employeeContr.getFirst_surname() +
                employeeContr.getSecond_surname())) {
            listErrorRequest.add(new ErrorRequest(DESCRIPTION_ERROR_NAME, CODE_ERROR_NAME));
        }

        if (!validateIdentification(employeeContr.getIdentification_card())) {
            listErrorRequest.add(new ErrorRequest(DESCRIPTION_ERROR_INDENTIFICATION, CODE_ERROR_INDENTIFICATION));
        }

        if (!validateMail(employeeContr.getEmail())) {
            listErrorRequest.add(new ErrorRequest(DESCRIPTION_ERROR_MAIL, CODE_ERROR_MAIL));
        }
        return ResponseEntity.badRequest().body(listErrorRequest);
    }

}
