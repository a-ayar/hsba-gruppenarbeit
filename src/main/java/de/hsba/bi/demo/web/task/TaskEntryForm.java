package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskEntryForm {

    private String solution;

    private User student;
}
