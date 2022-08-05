package de.hsba.bi.demo.web.subject;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class CreateSubjectController {

    private final SubjectService subjectService;
    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", userService.findAllTeacher());
        model.addAttribute("students", userService.findAllStudents());
        return "subjects/createSubject";
    }


    //zum Fachanlegen
    @PostMapping
    public String create(@RequestParam(name = "name") String name, @RequestParam(name = "teacher") Long teacherid , @RequestParam(name = "students") Long studentsid) {
        User teacher = userService.getUser(teacherid);
        User students = userService.getUser(studentsid);
        Subject subject = subjectService.createSubject(name, teacher, students);
        return "redirect:/subjects/";
    }
}
