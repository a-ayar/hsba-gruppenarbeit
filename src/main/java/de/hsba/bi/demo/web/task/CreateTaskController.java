package de.hsba.bi.demo.web.task;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.task.TaskService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tasks/createTask")
@RequiredArgsConstructor
@PreAuthorize("hasRole('LEHRER')")
public class CreateTaskController {

    private final TaskService taskService;
    private final TaskFormConverter taskFormConverter;
    private final SubjectService subjectService;
    private final UserAdapterService userAdapterService;

    @ModelAttribute("subjects")
    public List<Subject> getSubjects(){
        return subjectService.findAll();
    }



    @ModelAttribute("teacherSubjects")
    public List<Subject> getTeacherSubjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAdapter userAdapter = userAdapterService.loadUserByUsername(auth.getName());

        return subjectService.getSubjectById(userAdapter.getId());
    }


    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAll()); // Vielleicht überflüssig
        model.addAttribute("taskForm", new TaskForm());
        return "tasks/createTask";
    }

    @PostMapping
    public String createTask(@ModelAttribute("taskForm") @Valid TaskForm taskForm, BindingResult taskBinding, Model model) {
        if (taskBinding.hasErrors()){
            model.addAttribute("taskForm", taskForm);
            return "tasks/createTask";
        }
        taskService.save(taskFormConverter.update(new Task(), taskForm));

        return "redirect:/tasks/";
    }
}
