package com.krugger.vacunacion.Utils;

import com.krugger.vacunacion.entities.Authenticator;
import com.krugger.vacunacion.entities.Employee;
import com.krugger.vacunacion.entities.Role;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;

public class Util {
    public static final Authenticator generateAutheticator(Employee employee, Role rol) {
        Authenticator authenticator = new Authenticator(generateNickName(employee), generatedPassword(employee), employee, rol);
        return authenticator;

    }

    public static final String generateNickName(Employee employee) {
        Random r = new Random();
        int cantidadname = r.nextInt(90) + 10;
        String nickName = employee.getFirstname() + "." + employee.getFirst_surname() + "." + cantidadname;

        return nickName;
    }

    public static final String generatedPassword(Employee employee) {
        return RandomStringUtils.random(6, 0, 0, true, true, null, new SecureRandom());
    }


}
