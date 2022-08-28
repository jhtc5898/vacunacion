package com.krugger.vacunacion.pojo.employee;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VaccineEmployeePojo {
    public String identification_card;
    public String nameVaccine;
    public String dateVaccine;
    public Long dosis;
}
