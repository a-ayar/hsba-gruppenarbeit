package de.hsba.bi.demo.web.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserForm {

    @NotBlank(message = "Bitte geben sie einen Namen ein")
    private String name;

    @NotBlank(message = "Bitte geben sie einen Usernamen ein")
    private String username;

    @NotBlank(message = "Bitte geben sie ein Passwort ein")
    private String password;

    @NotBlank(message = "Bitte geben sie eine Rolle ein")
    private String role;
}
