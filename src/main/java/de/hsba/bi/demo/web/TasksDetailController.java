package de.hsba.bi.demo.web;

import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.task.TaskEntry;
import de.hsba.bi.demo.task.TaskService;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksDetailController {
//Abhängigkeiten deklarieren
    private final TaskService taskService;
    private final UserService userService;
//Abhängigkeiten nutzen
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "tasks/index";
    }

    @PostMapping
    public String create(String title, String description,String subject, String status) {
        Task task = taskService.createTask(title, description, subject, status);
        return "redirect:/tasks/";
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("users", userService.findAll());//
        model.addAttribute("task", taskService.getTask(id));
        return "tasks/tasksDetail";
    }


    @PostMapping(path = "/{id}")
    public String addEntry(@PathVariable("id") Long id, TaskEntry entry) {
        Task task = taskService.getTask(id);
        taskService.addTaskEntry(task, entry);
        return "redirect:/tasks/" + id;}

}
/*

    //Abhängigkeit deklarieren


    public TasksDetailController(TaskService taskService) {
        this.taskService = taskService;
    }

    private final TaskService taskService;
    private final UserService userService;
    //Abhängigkeit verwenden damit Controller keinen änderbaren Zustand hat

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAll());
        return "tasks/createTask";
    }

    @PostMapping
    public String create(Long id, String title, String description,String subject, String status) {
        Task task = taskService.createTask(title, description, subject, status);
        return "redirect:/tasks/";
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("users", userService.findAll());//
        model.addAttribute("task", taskService.getTask(id));
        return "tasks/tasksDetail";
    }


    @PostMapping(path = "/{id}")
    public String addEntry(@PathVariable("id") Long id, TaskEntry entry) {
        Task task = taskService.getTask(id);
        taskService.addTaskEntry(task, entry);
        return "redirect:/tasks/" + id;}*/