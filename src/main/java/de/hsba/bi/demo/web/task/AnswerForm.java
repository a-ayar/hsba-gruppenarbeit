package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.task.Evaluation;
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

    private User student;

    private Evaluation evaluation;

    private String comment;
}
