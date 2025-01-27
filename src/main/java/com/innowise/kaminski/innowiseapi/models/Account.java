package com.innowise.kaminski.innowiseapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@Table(name = "Accounts")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Getter
    @Setter
    @Column(name="username")
    @NotBlank
    private String username;

    @Getter
    @Setter
    @Column(name="password")
    @NotBlank
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
