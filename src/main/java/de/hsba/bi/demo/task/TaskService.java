package de.hsba.bi.demo.task;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collection;
import javax.transaction.Transactional;

@Service // da von dieser Klasse ein Objekt erstellt werden soll - Aylin
@Transactional
@RequiredArgsConstructor

public class TaskService {

    private final TaskRepository repository;
    private final TaskEntryRepository entryRepository;

    //Methoden für verschiedene Funktionen - Aylin
    // zum löschen einer Aufgabe -Aylin
    public void deleteTask(Long taskId){
        repository.deleteById(taskId);
    }

    //Aufgabe aufrufen - Aylin
    public Task getTask(Long taskId) {
        return repository.findById(taskId).orElse(null);
    }
    //Aufgaben aufrufen - Aylin
    public Collection<Task> getAll() {
        return repository.findAll();
    }

    //Aufgabe speicher speichern - Aylin
    public Task save(Task task) {
        return repository.save(task);
    }

    // Aufgabe erstellen - Aylin
    public Task createTask(String title, String description, Subject subject, Status status) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setSubject(subject);
        task.setStatus(status);
        return repository.save(task);
    }
    //Aufgabe veröffentlichen - Aylin
    public void publishTask(Long taskId){
        Task task = getTask(taskId);
        task.setStatus(Status.VERÖFFENTLICHT);
    }
    //Aufgabe schließen - Aylin
    public void closeTask(Long taskId){
        Task task = getTask(taskId);
        task.setStatus(Status.GESCHLOSSEN);
    }
    //Aufgabe in den Bearbeitungszustand stellen - Aylin
    public void editTask(Long taskId){
        Task task = getTask(taskId);
        task.setTaskIsOnEdit(true);
    }
    //Bearbeitung abbrechen - Aylin
    public void abortEditTask(Long taskId) {
        Task task = getTask(taskId);
        task.setTaskIsOnEdit(false);
    }
    //Berarbeitete Aufgabe anlegen - Aylin
    public void saveNewTask(Long taskId, String newTitle, String newDescription, Subject newSubject){
        Task task = getTask(taskId);
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        task.setSubject(newSubject);
        task.setStatus(Status.INITIAL);
        task.setTaskIsOnEdit(false);
    }

// Abschnitt für die Antworten - Aylin

    //Lösung eingeben - Aylin
    public void addTaskEntry(Task task, TaskEntry entry) {
        entry.setTask(task);
        task.getEntries().add(entry);
    }
    //Antworten abfragen - Aylin
    public TaskEntry getTaskEntry(Long taskId, Long entryId){
        Task task = repository.getReferenceById(taskId);
        TaskEntry taskEntry = task.getEntryById(entryId);
        return taskEntry;
    }
        //Antwort löschen - Aylin
    public void deleteAnswer(Long taskId, Long entryId) {
        //get current task, get TaskEntry and delete in List - Aylin
        TaskEntry entryToRemove = getTaskEntry (taskId, entryId);
        repository.getReferenceById(taskId).getEntries().remove(entryToRemove);}

    //Anwort in Bearbeitungszustand versetzen - Aylin
    public void editAnswer(Long id, Long entryId){
        TaskEntry taskEntry = getTaskEntry(id, entryId);
        taskEntry.setAnswerIsOnEdit(true);
    }

    //Bearbeitung der Antwort abbrechen - Aylin
    public void abortEditAnswer(Long id, Long entryId){
        TaskEntry taskEntry = getTaskEntry(id, entryId);
        taskEntry.setAnswerIsOnEdit(false);
    }
    // Bearbeitete Anwort speichern - Aylin
    public void saveNewAnswer(Long id, Long entryId, String newAnswer){
        TaskEntry taskEntry = getTaskEntry(id, entryId);
        taskEntry.setSolution(newAnswer);
        taskEntry.setAnswerIsOnEdit(false);
    }

    //Zustands veränderung um die Bewertung einzugeben - Aylin
    public void editEvaluation(Long id, Long entryId){
        TaskEntry taskEntry = getTaskEntry(id, entryId);
        taskEntry.setEvaluationIsOnEdit(true);
    }
    //Bearbeitung der Bewertung abbrechen - Aylin
    public void abortEditEvaluation(Long id, Long entryId){
        TaskEntry taskEntry = getTaskEntry(id, entryId);
        taskEntry.setEvaluationIsOnEdit(false);
    }
    // Bearbeitete Anwort speichern - Aylin
    public void saveNewEvaluation(Long id, Long entryId, Evaluation newEvaluation, String comment){
        TaskEntry taskEntry = getTaskEntry(id, entryId);
        taskEntry.setEvaluation(newEvaluation);
        taskEntry.setComment(comment);
        taskEntry.setEvaluationIsOnEdit(false);
    }

}
