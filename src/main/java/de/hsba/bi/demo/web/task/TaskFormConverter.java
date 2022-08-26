package de.hsba.bi.demo.web.task;

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
        form.setTitle(task.getTitle());
        form.setTitle(task.getTitle());
        form.setTitle(task.getTitle());
        return task;
    }

    TaskEntryForm toForm(TaskEntry entry) {
        TaskEntryForm form = new TaskEntryForm();
        form.setSolution(entry.getSolution());
        form.setStudent(entry.getStudent()
        );
        return form;
    }

    TaskEntry update(TaskEntry entry, TaskEntryForm form) {
        entry.setSolution(form.getSolution());
        entry.setStudent(form.getStudent());
        return entry;
    }
}
