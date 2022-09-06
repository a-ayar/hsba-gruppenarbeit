package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AnswerForm {

    @NotBlank(message = "Bitte geben sie eine Antwort ein.")
    private String solution;

    @NotNull (message = "User fehlt, bitte geben sie ein User an.")
    private User student;
}
