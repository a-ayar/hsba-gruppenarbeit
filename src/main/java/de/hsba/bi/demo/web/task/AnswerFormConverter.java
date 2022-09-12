package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.task.Evaluation;
import de.hsba.bi.demo.task.Status;
import de.hsba.bi.demo.task.TaskEntry;
import org.springframework.stereotype.Component;

@Component
public class AnswerFormConverter {


    AnswerForm toForm(TaskEntry taskEntry) {
        AnswerForm form = new AnswerForm();
        form.setSolution(taskEntry.getSolution());
        form.setStudent(taskEntry.getStudent());
        form.setEvaluation(taskEntry.getEvaluation());
        form.setComment(taskEntry.getComment());
        return form;
    }

    TaskEntry update(TaskEntry taskEntry, AnswerForm form) {
        taskEntry.setSolution(form.getSolution());
        taskEntry.setStudent(form.getStudent());
        taskEntry.setEvaluation(Evaluation.UNBENOTET);
        taskEntry.setComment(form.getComment());
        return taskEntry;
    }
}
