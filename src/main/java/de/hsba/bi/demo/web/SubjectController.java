package de.hsba.bi.demo.web;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectEntry;
import de.hsba.bi.demo.subject.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")//1
public class SubjectController {

   // private Subject subject;

    //Abhängigkeit deklarieren
    private final SubjectService subjectService;//2

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //Abhängigkeit verwenden damit Controller keinen änderbaren Zustand hat

    @GetMapping
    public String index(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        return "menu/index";
    }

    @PostMapping
    public String create(String name) {
        Subject subject = subjectService.createSubject(name);
        return "redirect:/menu/" + subject.getId();
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("subject", subjectService.getSubject(id));
        return "menu/subjects";
    }


    @PostMapping(path = "/{id}")
    public String addEntry(@PathVariable("id") Long id, SubjectEntry entry) {
        Subject subject = subjectService.getSubject(id);
        subjectService.addSubjectEntry(subject, entry);
        return "redirect:/menu/" + id;
    }

}
