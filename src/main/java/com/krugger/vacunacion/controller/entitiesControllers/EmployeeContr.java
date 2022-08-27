package com.krugger.vacunacion.controller.entitiesControllers;


import lombok.Builder;
import lombok.Data;
import lombok.With;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class EmployeeContr {
    @Size(min = 10, max = 10, message = "About Me must be between 10 and 10 characters")
    @With
    private String identification_card;

    @With
    private String first_name;

    @With
    private String second_name;

    @With
    private String first_surname;

    @With
    private String second_surname;

    @NotNull(message = "The identification card not null")
    @Email(message = "Email should be valid")
    @With
    private String email;


}

