package de.hsba.bi.demo.web.subject;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.task.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class CreateSubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subjects/createSubject";
    }
    //zum Fachanlegen
    @PostMapping
    public String create(String name, String teacher,String students) {
        Subject subject = subjectService.createSubject(name, teacher, students);
        return "redirect:/subjects/";
    }
}
