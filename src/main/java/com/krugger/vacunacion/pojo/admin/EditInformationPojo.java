package com.krugger.vacunacion.pojo.admin;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class EditInformationPojo {
    @NotNull(message = "The identification card not null")
    @Size(min = 10, max = 10, message = "About Me must be between 10 and 10 characters")
    @With
    private String identification_card;

    @NotNull(message = "The Email not null")
    @Email(message = "Email should be valid")
    @With
    private String email;

    @NotNull(message = "statusVaccine")
    private Boolean statusVaccine;


}
