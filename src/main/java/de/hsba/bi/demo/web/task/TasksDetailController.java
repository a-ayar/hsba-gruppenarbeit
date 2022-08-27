package de.hsba.bi.demo.web.task;

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


    @PostMapping(path = "/{id}/editTask")
    public String editTask(@PathVariable("id") Long id) {
        taskService.editTask(id);
        return "redirect:/tasks/";
    }

    @PostMapping(path = "/{id}/saveNewTask")
    public String saveNewTask(@PathVariable("id") Long taskId, @RequestParam(name = "newTitle")String title,  @RequestParam(name = "newDescription")String description, @RequestParam(name = "newSubject")Subject subject,  @RequestParam(name = "newStatus")String status) {
        taskService.saveNewTask(taskId, title, description, subject, status);
        return "redirect:/tasks";
    }

    @PostMapping(path = "/{id}/deleteTask")
    public String deleteTask(@PathVariable("id") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks";
    }


    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("students", userService.findAllStudents());//
        model.addAttribute("task", taskService.getTask(id));
        return "tasks/tasksDetail";
    }


//    @PostMapping(path = "/{id}")
//public String addEntry(@PathVariable("id") Long id, TaskEntry entry) {
//        Task task = taskService.getTask(id);
//        taskService.addTaskEntry(task, entry);
//        return "redirect:/tasks/" + id;
//    }



    @PostMapping(path = "/{id}")
    public String addEntry(@PathVariable("id") Long id, @RequestParam(name = "solution")String solution, @RequestParam(value = "student")Long student) {
        Task task = taskService.getTask(id);
        TaskEntry newEntry = new TaskEntry(solution, userService.getUser(student));
        taskService.addTaskEntry(task, newEntry);
        return "redirect:/tasks/" + id;}



    @PostMapping(path = "/{id}/{entryID}/delete")
    public String delete(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.deleteAwnser(id, entryId);
        return "redirect:/tasks/{id}";
    }


    @PostMapping(path = "/{id}/{entryID}/edit")
    public String edit(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId) {
        taskService.editAnswer(id, entryId);
        return "redirect:/tasks/{id}";
    }

    @PostMapping(path = "/{id}/{entryID}/saveNewAnswer")
    public String saveNewAnswer(@PathVariable("id") Long id, @PathVariable("entryID") Long entryId, @RequestParam(name = "newSolution")String solution ) {
        taskService.saveNewAnswer(id, entryId, solution);
        return "redirect:/tasks/{id}";
    }


}
