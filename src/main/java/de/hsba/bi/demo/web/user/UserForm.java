package de.hsba.bi.demo.web.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserForm {

    @NotBlank(message = "Bitte geben sie einen Namen ein")
    private String name;

    @NotBlank(message = "Bitte geben sie einen Usernamen ein")
    private String username;

    @Size(min= 4, message = "Passwort enthält zu wenig Zeichen")
    private String password;

    @NotBlank(message = "Bitte wählen Sie eine Rolle")
    private String role;
}
