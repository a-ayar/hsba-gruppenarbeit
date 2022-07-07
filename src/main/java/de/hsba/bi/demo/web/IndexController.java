package de.hsba.bi.demo.web;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    private Subject subject;

    public IndexController() {
        subject = new Subject();
        // add some entries for testing

        subject.getEntries().add(new SubjectEntry( Integer.valueOf(15), "Mathe", "Aylin"));
        subject.getEntries().add(new SubjectEntry( Integer.valueOf(10), "Deutsch", "Aylin"));
    }

    @GetMapping("/")
    public String show(Model model) {
        model.addAttribute("subject", subject);
        return "index";
    }

    @PostMapping("/add")
    public String addSubject(SubjectEntry entry) {
        subject.getEntries().add(entry);
        return "redirect:/";
    }

}
