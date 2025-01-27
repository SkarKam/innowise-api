package com.innowise.kaminski.innowiseapi.models.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
public class RegisterDTO {

    @Getter
    @Setter
    @Valid
    @NotBlank(message = "Username cannot be empty!")
    private String username;

    @Getter
    @Setter
    @Valid
    @NotBlank(message = "Password cannot be empty!")
    private String password;
}
