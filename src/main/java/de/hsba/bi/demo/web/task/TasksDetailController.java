package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.task.Evaluation;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.task.TaskEntry;
import de.hsba.bi.demo.task.TaskService;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserAdapter;
import de.hsba.bi.demo.user.UserAdapterService;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksDetailController {
//Abhängigkeiten deklarieren - Aylin
    private final TaskService taskService;
    private final UserService userService;
    private final SubjectService subjectService;
    private final TaskFormConverter taskFormConverter;
    private final AnswerFormConverter answerFormConverter;
    private final UserAdapterService userAdapterService;

    //Alle Aufgaben - Aylin
    @ModelAttribute("tasks")
    public Collection<Task> getTasks(){
        return taskService.getAll();
    }
    //Alle Fächer - Aylin
    @ModelAttribute("subjects")
    public List<Subject> getSubjects(){
        return subjectService.findAll();
    }
    //Eingelogter User beim angeben der Antworten - Aylin
    @ModelAttribute("loggedInStudent")
    public User findCurrentStudent() {return userService.findCurrentUser();}
    //Die Aufgaben eines Lehrers - Marc
    @ModelAttribute("teacherTasks")
    public Collection<Task> getTeacherTasks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAdapter userAdapter = userAdapterService.loadUserByUsername(auth.getName());

        return taskService.getTaskById(userAdapter.getId());
    }
    //Die Aufgaben des Schülers - Marc
    @ModelAttribute("studentTasks")
    public Collection<Task> getStudentTasks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAdapter userAdapter = userAdapterService.loadUserByUsername(auth.getName());

        return taskService.getTaskByStuId(userAdapter.getId());
    }
    //Die Fächer eines Schülers - Marc
    @ModelAttribute("studentSubjects")
    public List<Subject> getStudentSubjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAdapter userAdapter = userAdapterService.loadUserByUsername(auth.getName());

        return subjectService.getSubjectByStuId(userAdapter.getId());
    }
    //Die Fächer eines Lehrers - Marc
    @ModelAttribute("teacherSubjects")
    public List<Subject> getTeacherSubjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAdapter userAdapter = userAdapterService.loadUserByUsername(auth.getName());

        return subjectService.getSubjectById(userAdapter.getId());
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("taskForm", new TaskForm());//sichergestellt das html view immer ein Formular hat - Aylin
        return "tasks/index";
    }
    // bearbeitete Aufgabe speichern -Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/saveNewTask")
    public String saveNewTask(@PathVariable("id") Long taskId, @ModelAttribute("taskForm") @Valid TaskForm taskForm, BindingResult taskBinding, Model model) {
        if (taskBinding.hasErrors()){
            model.addAttribute("taskForm", taskForm);
            return "tasks/index";
        }
        Task task = taskFormConverter.update(taskService.getTask(taskId), taskForm);
        taskService.save(task);
        return "redirect:/tasks";
    }

    //Aufgabe bearbeiten -Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/editTask")
    public String editTask(@PathVariable("id") Long id) {
        taskService.editTask(id);
        return "redirect:/tasks/";
    }
    //Aufgabe bearbeiten abbrechen -Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/abortEditTask")
    public String abborteditTask(@PathVariable("id") Long id) {
        taskService.abortEditTask(id);
        return "redirect:/tasks/";
    }
    //Aufgabe löschen -Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/deleteTask")
    public String deleteTask(@PathVariable("id") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks";
    }
    //Aufgabe veröffentlichen -Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/publishTask")
    public String publishTask(@PathVariable("id") Long taskId) {
        taskService.publishTask(taskId);
        return "redirect:/tasks";
    }
    //Aufgabe schließen -Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/closeTask")
    public String closeTask(@PathVariable("id") Long taskId) {
        taskService.closeTask(taskId);
        return "redirect:/tasks";
    }

    //Answer Abschnitt - Aylin

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("students", userService.findAllStudents());
        model.addAttribute("task", taskService.getTask(id));
        model.addAttribute("answerForm", new AnswerForm());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAdapter userAdapter = userAdapterService.loadUserByUsername(auth.getName());
        model.addAttribute("taskEntries", taskService.getTaskEntriesByStudentId(userAdapter.getId(), id));

        return "tasks/tasksDetail";
    }

    //Lösung eintragen -Aylin
@PreAuthorize("hasRole('SCHÜLER')")
    @PostMapping(path = "/{id}")
    public String addEntry(@PathVariable("id") Long id, @ModelAttribute("answerForm") @Valid AnswerForm answerForm, BindingResult answerBinding, Model model) {
        if (answerBinding.hasErrors()){
            model.addAttribute("answerForm", answerForm);
            return "redirect:/tasks/" + id;
        }
        Task task = taskService.getTask(id);
        taskService.addTaskEntry( task, answerFormConverter.update(new TaskEntry(), answerForm, findCurrentStudent()));

        return "redirect:/tasks/" + id;
    }

    //Lösung löschen -Aylin
    @PreAuthorize("hasRole('SCHÜLER')")
    @PostMapping(path = "/{id}/{entryID}/deleteAnswer")
    public String deleteAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.deleteAnswer(id, entryId);
        return "redirect:/tasks/{id}";
    }
    //Lösung bearbeiten -Aylin
    @PreAuthorize("hasRole('SCHÜLER')")
    @PostMapping(path = "/{id}/{entryID}/editAnswer")
    public String editAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.editAnswer(id, entryId);
        return "redirect:/tasks/{id}";
    }
    //Lösung bearbeiten abbrechen-Aylin
    @PreAuthorize("hasRole('SCHÜLER')")
    @PostMapping(path = "/{id}/{entryID}/abortEditAnswer")
    public String abortEditAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.abortEditAnswer(id, entryId);
        return "redirect:/tasks/{id}";
    }

    //Neue Lösung speichern -Aylin
    @PreAuthorize("hasRole('SCHÜLER')")
    @PostMapping(path = "/{id}/{entryID}/saveNewAnswer")
    public String saveNewAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId, @ModelAttribute("answerForm") @Valid AnswerForm answerForm, BindingResult answerBinding, Model model  ) {
        if (answerBinding.hasErrors()) {
            model.addAttribute("answerForm", answerForm);
            return "redirect:/tasks/{id}";
        }
        Task task = taskService.getTask(id);
        TaskEntry taskEntry = answerFormConverter.update(taskService.getTaskEntry(id,entryId), answerForm, findCurrentStudent());
        taskService.addTaskEntry(task, taskEntry);
        return "redirect:/tasks/{id}";
    }
    //Bewertung bearbeiten -Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/{entryID}/editEvaluation")
    public String editEvaluation(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.editEvaluation(id, entryId);
        return "redirect:/tasks/{id}";
    }
    //Bewertung bearbeiten abbrechen-Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/{entryID}/abortEditEvaluation")
    public String abortEditEvaluation(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.abortEditEvaluation(id, entryId);
        return "redirect:/tasks/{id}";
    }
    //neue Bewertung speichern -Aylin
    @PreAuthorize("hasRole('LEHRER')")
    @PostMapping(path = "/{id}/{entryID}/saveNewEvaluation")
    public String saveNewEvaluation(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId, @RequestParam(name = "newEvaluation")Evaluation evaluation, @RequestParam(name = "comment") String comment) {
        taskService.saveNewEvaluation(id, entryId, evaluation, comment);
        return "redirect:/tasks/{id}";
    }


}
