package de.hsba.bi.demo.web;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.solution.Solution;
import de.hsba.bi.demo.solution.SolutionService;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solutions")
@RequiredArgsConstructor
public class SolutionController {

    private final SolutionService solutionService;
    private final TaskService taskService;
    private final SubjectService subjectService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("solutions", solutionService.findAll());
        model.addAttribute("tasks", taskService.getAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "solutions/createSolutions";
    }

    @PostMapping
    public String create(String mark, String comment) {
        Solution solution = solutionService.createSolution(mark, comment);
        return "redirect:/solutions/";
    }
}