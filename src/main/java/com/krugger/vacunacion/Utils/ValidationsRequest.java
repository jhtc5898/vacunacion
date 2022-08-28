package com.krugger.vacunacion.Utils;

import com.krugger.vacunacion.pojo.admin.AddEmployeePojo;
import com.krugger.vacunacion.pojo.employee.UpdateEmployeePojo;
import com.krugger.vacunacion.pojo.employee.VaccineEmployeePojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.krugger.vacunacion.Utils.Constants.IDENTIFICACTION_SIZE;
import static com.krugger.vacunacion.Utils.Parameters.DATE_FORMAT;

public class ValidationsRequest {

    public static final Boolean validation_request(AddEmployeePojo employeePojo) {

        if (validateIdentification(
                employeePojo.getIdentification_card()) &&
                validateMail(employeePojo.getEmail()) &&
                validateName(
                        employeePojo.getFirst_name() +
                                employeePojo.getSecond_name() +
                                employeePojo.getFirst_surname() +
                                employeePojo.getSecond_surname())
        ) {
            return true;
        }
        return false;
    }

    public static final Boolean validation_request(UpdateEmployeePojo employeePojo) {
        if (validateDate(employeePojo.getDate_birth()) && validateIdentification(employeePojo.getIdentification_card())) {
            return Boolean.TRUE;
        }
        return false;

    }

    public static final Boolean validation_request(VaccineEmployeePojo vaccineEmployeePojo) {
        if (validateDate(vaccineEmployeePojo.getDateVaccine()) && vaccineEmployeePojo.getNameVaccine() != null && vaccineEmployeePojo.getDosis() != 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

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

    public static final Boolean validateDate(String date) {
        boolean correcto = false;
        try {
            //Formato de fecha (día/mes/año)
            SimpleDateFormat formatoFecha = new SimpleDateFormat(DATE_FORMAT);
            formatoFecha.setLenient(false);
            //Comprobación de la fecha
            formatoFecha.parse(date);
            correcto = true;
        } catch (ParseException e) {
            //Si la fecha no es correcta, pasará por aquí
            correcto = false;
        }

        return correcto;
    }

}
