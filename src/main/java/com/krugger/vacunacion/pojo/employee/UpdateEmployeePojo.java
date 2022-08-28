package com.krugger.vacunacion.pojo.employee;

import com.krugger.vacunacion.entities.Direction;
import com.krugger.vacunacion.entities.Phone;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateEmployeePojo {
    private String identification_card;
    private String date_birth;
    private Direction direction;
    private Phone phone;
    private Boolean status_vaccine;

}
