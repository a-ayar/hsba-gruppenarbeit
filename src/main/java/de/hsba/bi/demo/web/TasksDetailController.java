package de.hsba.bi.demo.web;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.task.TaskEntry;
import de.hsba.bi.demo.task.TaskService;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksDetailController {
//Abhängigkeiten deklarieren
    private final TaskService taskService;
    private final UserService userService;
    private final SubjectService subjectService;

//Abhängigkeiten nutzen
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "tasks/index";
    }

    @PostMapping
    public String create(@RequestParam(name = "title")String title, @RequestParam(name = "description") String description, @RequestParam(name = "subject") Long subjectid, @RequestParam(name = "status") String status) {
        Subject subject = subjectService.getSubject(subjectid);
        Task task = taskService.createTask(title, description, subject, status);
        return "redirect:/tasks/";
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("students", userService.findAllStudents());//
        model.addAttribute("task", taskService.getTask(id));
        return "tasks/tasksDetail";
    }


    @PostMapping(path = "/{id}")
    public String addEntry(@PathVariable("id") Long id, TaskEntry entry) {
        Task task = taskService.getTask(id);
        taskService.addTaskEntry(task, entry);
        return "redirect:/tasks/" + id;}

}
