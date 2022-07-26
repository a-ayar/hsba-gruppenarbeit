package de.hsba.bi.demo.task;

import org.springframework.stereotype.Service;
import java.util.Collection;
import javax.transaction.Transactional;

@Service // da von dieser Klasse ein Objekt erstellt werden soll
@Transactional

//Service mit den Methoden
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

// Aufgabe erstellen
    public Task createTask(String title, String description, String subject, String status) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setSubject(subject);
        task.setStatus(status);
        return repository.save(task);
    }
    //Aufgabe speicher speichern
    public Task save(Task task) {
        return repository.save(task);
    }

    //Aufgabe aufrufen

    public Task getTask(Long id) {
        return repository.findById(id).orElse(null);
    }

    //Lösung eingeben

    public void addTaskEntry(Task task, TaskEntry entry) {
        entry.setTask(task);
        task.getEntries().add(entry);
    }

    public Collection<Task> getAll() {
        return repository.findAll();
    }
}
