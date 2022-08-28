package com.krugger.vacunacion.exceptions;

import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.krugger.vacunacion.Utils.Constants.*;
import static com.krugger.vacunacion.Utils.ValidationsRequest.*;

public class ErrorValidate {
    public static ResponseEntity<Object> errorValidate(AddEmployeePojo employeePojo) {
        List<ErrorRequest> listErrorRequest = new ArrayList<>();

        if (!validateName(employeePojo.getFirst_name() +
                employeePojo.getSecond_name() +
                employeePojo.getFirst_surname() +
                employeePojo.getSecond_surname())) {
            listErrorRequest.add(new ErrorRequest(DESCRIPTION_ERROR_NAME, CODE_ERROR_NAME));
        }

        if (!validateIdentification(employeePojo.getIdentification_card())) {
            listErrorRequest.add(new ErrorRequest(DESCRIPTION_ERROR_INDENTIFICATION, CODE_ERROR_INDENTIFICATION));
        }

        if (!validateMail(employeePojo.getEmail())) {
            listErrorRequest.add(new ErrorRequest(DESCRIPTION_ERROR_MAIL, CODE_ERROR_MAIL));
        }
        return ResponseEntity.badRequest().body(listErrorRequest);
    }

    public static ResponseEntity<Object> errorValidate(UpdateEmployeePojo updateEmployeePojo) {
        List<ErrorRequest> listErrorRequest = new ArrayList<>();
        if (!validateDate(updateEmployeePojo.getDate_birth())) {
            listErrorRequest.add(new ErrorRequest(DESCRIPTION_ERROR_DATE, CODE_ERROR_DATE));
        }
        return ResponseEntity.badRequest().body(listErrorRequest);

    }

//    public static ResponseEntity<Object> errorValidate(VaccineEmployeePojo vaccineEmployeePojo) {
//        List<ErrorRequest> listErrorRequest = new ArrayList<>();
//        if (!validateDate(vaccineEmployeePojo)) {
//            listErrorRequest.add(new ErrorRequest(DESCRIPTION_ERROR_DATE, CODE_ERROR_DATE));
//        }
//        return ResponseEntity.badRequest().body(listErrorRequest);
//    }

}
