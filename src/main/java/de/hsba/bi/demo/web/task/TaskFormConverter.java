package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.task.Status;
import de.hsba.bi.demo.task.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskFormConverter {

    Task update(Task task, TaskForm form) {
        task.setTitle(form.getTitle());
        task.setDescription(form.getDescription());
        task.setSubject(form.getSubject());
        task.setStatus(Status.INITIAL);
        task.setTaskIsOnEdit(false);
        return task;
    }



}
