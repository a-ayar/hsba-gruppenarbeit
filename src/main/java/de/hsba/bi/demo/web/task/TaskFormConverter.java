package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.task.Status;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.task.TaskEntry;
import org.springframework.stereotype.Component;

@Component
public class TaskFormConverter {

    TaskForm toForm(Task task) {
        TaskForm form = new TaskForm();
        form.setTitle(task.getTitle());
        form.setDescription(task.getDescription());
        form.setSubject(task.getSubject());
        form.setStatus(task.getStatus());
        return form;
    }

    Task update(Task task, TaskForm form) {
        task.setTitle(form.getTitle());
        task.setDescription(form.getDescription());
        task.setSubject(form.getSubject());
        task.setStatus(Status.INITIAL);
        task.setTaskIsOnEdit(false);
        return task;
    }



}
