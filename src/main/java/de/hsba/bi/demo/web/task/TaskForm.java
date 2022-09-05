package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.task.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskForm {

        @NotBlank(message = "Bitte geben Sie ein Titel für Ihre Aufgabe an")
        private String title;

        @NotBlank(message = "Bitte geben Sie ein Beschreibung für Ihre Aufgabe an")
        private String description;

        @NotNull(message = "Bitte geben Sie das dazugehörige Fach an")
        private Subject subject;

        private Status status;

    }