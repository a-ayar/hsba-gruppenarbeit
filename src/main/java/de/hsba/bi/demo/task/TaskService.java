package de.hsba.bi.demo.task;

import de.hsba.bi.demo.subject.Subject;
import org.springframework.stereotype.Service;
import java.util.Collection;
import javax.transaction.Transactional;

@Service // da von dieser Klasse ein Objekt erstellt werden soll
@Transactional

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Aufgabe erstellen
    public Task createTask(String title, String description, Subject subject, String status) {
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
    public Task getTask(Long taskId) {
        return repository.findById(taskId).orElse(null);
    }

    public void editTask(Long taskId){
        Task task = getTask(taskId);
        task.setTaskIsOnEdit(true);
    }
    public void saveNewTask(Long taskId, String newTitle, String newDescription, Subject newSubject, String newStatus){
        Task task = getTask(taskId);
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        task.setSubject(newSubject);
        task.setStatus(newStatus);
        task.setTaskIsOnEdit(false);
    }
    // zum löschen einer Aufgabe
    public void deleteTask(Long taskId){
        repository.deleteById(taskId);
    }

    public Collection<Task> getAll() {
        return repository.findAll();
    }

    //Lösung eingeben
    public void addTaskEntry(Task task, TaskEntry entry) {
        entry.setTask(task);
        task.getEntries().add(entry);
    }

    public TaskEntry getTaskEntry(Long taskId, Long entryId){
        Task task = repository.getReferenceById(taskId);
        TaskEntry taskEntry = task.getEntryById(entryId);
        return taskEntry;
    }

    public void deleteAwnser(Long taskId, Long entryId) {
        //get current task, get TaskEntry and delete in List
        TaskEntry entryToRemove = getTaskEntry (taskId, entryId);
        repository.getReferenceById(taskId).getEntries().remove(entryToRemove);}


    public void editAnswer(Long id, Long entryId){
        TaskEntry taskEntry = getTaskEntry(id, entryId);
        taskEntry.setOnEdit(true);
    }
    public void saveNewAnswer(Long id, Long entryId, String newAnswer){
        TaskEntry taskEntry = getTaskEntry(id, entryId);
        taskEntry.setSolution(newAnswer);
        taskEntry.setOnEdit(false);
    }

}
