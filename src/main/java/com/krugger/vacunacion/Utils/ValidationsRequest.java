package com.krugger.vacunacion.Utils;

import com.krugger.vacunacion.controller.entitiesControllers.EmployeeContr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.krugger.vacunacion.Utils.Constants.IDENTIFICACTION_SIZE;

public class ValidationsRequest {

    public static final Boolean general_validation(EmployeeContr employeeContr) {

        if (validateIdentification(
                employeeContr.getIdentification_card()) &&
                validateMail(employeeContr.getEmail()) &&
                validateName(
                        employeeContr.getFirst_name() +
                                employeeContr.getSecond_name() +
                                employeeContr.getFirst_surname() +
                                employeeContr.getSecond_surname())
        ) {
            return true;
        }
        return false;
    }

    public static final Boolean validateName(String name) {

        Pattern pat = Pattern.compile("^[a-zA-Z]*$");
        Matcher mat = pat.matcher(name);
        if (mat.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static final Boolean validateMail(String mail) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(mail);
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }

    public static final Boolean validateIdentification(String identification_card) {
        if (identification_card.length() == IDENTIFICACTION_SIZE) {
            return true;
        }
        return false;
    }

}
