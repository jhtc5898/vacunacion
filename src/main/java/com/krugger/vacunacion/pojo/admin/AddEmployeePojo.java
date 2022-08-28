package com.krugger.vacunacion.pojo.admin;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddEmployeePojo {
    @NotNull(message = "The identification card not null")
    private String identification_card;

    @NotNull(message = "The first name not null")
    private String first_name;

    @NotNull(message = "The second name card not null")
    private String second_name;

    @NotNull(message = "The first surname not null")
    private String first_surname;

    @NotNull(message = "The second name card not null")
    private String second_surname;

    @NotNull(message = "The Email not null")
    private String email;


}

