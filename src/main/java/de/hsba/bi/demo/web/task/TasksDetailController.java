package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.task.Evaluation;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.task.TaskEntry;
import de.hsba.bi.demo.task.TaskService;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
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

//Abhängigkeiten nutzen - Aylin
    @GetMapping
    public String index(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        model.addAttribute("editTaskForm", new TaskForm());//sichergestellt das html view immer ein Formular hat - Aylin
        return "tasks/index";
    }

    @ModelAttribute("tasks")
    public Collection<Task> getTasks(){
      return taskService.getAll();
    }

    @ModelAttribute("subjects")
    public List<Subject> getSubjects(){
        return subjectService.findAll();
    }

    /**@PostMapping
    public String createTask(@RequestParam(name = "title")String title, @RequestParam(name = "description") String description, @RequestParam(name = "subject") Long subjectid) {
        Subject subject = subjectService.getSubject(subjectid);
        Task task = taskService.createTask(title, description, subject);
        return "redirect:/tasks/";
    }**/

    @PostMapping
    public String createTask(@ModelAttribute("taskForm") @Valid TaskForm taskForm, BindingResult taskBinding, Model model) {
        if (taskBinding.hasErrors()){
            model.addAttribute("taskForm", taskForm);
            return "tasks/index";
        }
        taskService.save(taskFormConverter.update(new Task(), taskForm));

        return "redirect:/tasks/";
    }
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



    @PostMapping(path = "/{id}/editTask")
    public String editTask(@PathVariable("id") Long id) {
        taskService.editTask(id);
        return "redirect:/tasks/";
    }

    @PostMapping(path = "/{id}/abortEditTask")
    public String abborteditTask(@PathVariable("id") Long id) {
        taskService.abortEditTask(id);
        return "redirect:/tasks/";
    }

    /*@PostMapping(path = "/{id}/saveNewTask")
    public String saveNewTask(@PathVariable("id") Long taskId, @RequestParam(name = "newTitle")String title,  @RequestParam(name = "newDescription")String description, @RequestParam(name = "newSubject")Subject subject) {
        taskService.saveNewTask(taskId, title, description, subject);
        return "redirect:/tasks";
    }*/

    @PostMapping(path = "/{id}/deleteTask")
    public String deleteTask(@PathVariable("id") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks";
    }

    @PostMapping(path = "/{id}/publishTask")
    public String publishTask(@PathVariable("id") Long taskId) {
        taskService.publishTask(taskId);
        return "redirect:/tasks";
    }

    @PostMapping(path = "/{id}/closeTask")
    public String closeTask(@PathVariable("id") Long taskId) {
        taskService.closeTask(taskId);
        return "redirect:/tasks";
    }



    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("students", userService.findAllStudents());//
        model.addAttribute("task", taskService.getTask(id));
        return "tasks/tasksDetail";
    }


    @PostMapping(path = "/{id}")
    public String addEntry(@PathVariable("id") Long id, @RequestParam(name = "solution")String solution, @RequestParam(value = "student")Long student) {
        Task task = taskService.getTask(id);
        TaskEntry newEntry = new TaskEntry(solution, userService.getUser(student));
        taskService.addTaskEntry(task, newEntry);
        return "redirect:/tasks/" + id;}



    @PostMapping(path = "/{id}/{entryID}/deleteAnswer")
    public String deleteAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.deleteAnswer(id, entryId);
        return "redirect:/tasks/{id}";
    }


    @PostMapping(path = "/{id}/{entryID}/editAnswer")
    public String editAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.editAnswer(id, entryId);
        return "redirect:/tasks/{id}";
    }
    @PostMapping(path = "/{id}/{entryID}/abortEditAnswer")
    public String abortEditAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.abortEditAnswer(id, entryId);
        return "redirect:/tasks/{id}";
    }

    @PostMapping(path = "/{id}/{entryID}/saveNewAnswer")
    public String saveNewAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId, @RequestParam(name = "newSolution")String solution ) {
        taskService.saveNewAnswer(id, entryId, solution);
        return "redirect:/tasks/{id}";
    }
    @PostMapping(path = "/{id}/{entryID}/editEvaluation")
    public String editEvaluation(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.editEvaluation(id, entryId);
        return "redirect:/tasks/{id}";
    }
    @PostMapping(path = "/{id}/{entryID}/abortEditEvaluation")
    public String abortEditEvaluation(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.abortEditEvaluation(id, entryId);
        return "redirect:/tasks/{id}";
    }

    @PostMapping(path = "/{id}/{entryID}/saveNewEvaluation")
    public String saveNewEvaluation(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId, @RequestParam(name = "newEvaluation")Evaluation evaluation, @RequestParam(name = "comment") String comment) {
        taskService.saveNewEvaluation(id, entryId, evaluation, comment);
        return "redirect:/tasks/{id}";
    }


}
