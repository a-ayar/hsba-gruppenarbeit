package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.task.Status;
import de.hsba.bi.demo.task.TaskEntry;
import org.springframework.stereotype.Component;

@Component
public class AnswerFormConverter {


    AnswerForm toForm(TaskEntry taskEntry) {
        AnswerForm form = new AnswerForm();
        form.setSolution(taskEntry.getSolution());
        form.setStudent(taskEntry.getStudent());
        return form;
    }

    TaskEntry update(TaskEntry taskEntry, AnswerForm form) {
        taskEntry.setSolution(form.getSolution());
        taskEntry.setStudent(form.getStudent());
        return taskEntry;
    }
}
