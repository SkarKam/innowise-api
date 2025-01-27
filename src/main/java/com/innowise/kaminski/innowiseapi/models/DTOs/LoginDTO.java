package com.innowise.kaminski.innowiseapi.models.DTOs;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
public class LoginDTO {

    @Getter
    @Setter
    @NotBlank
    private String username;


    @Getter
    @Setter
    @NotBlank
    private String password;
}
