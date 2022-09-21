package de.hsba.bi.demo.web.subject;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.subject.SubjectService;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserAdapter;
import de.hsba.bi.demo.user.UserAdapterService;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class CreateSubjectController {

    private final SubjectService subjectService;
    private final UserService userService;
    private final SubjectFormConverter subjectFormConverter;
    private final UserAdapterService userAdapterService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", userService.findAllTeacher());
        model.addAttribute("students", userService.findAllStudents());
        return "admin/createSubject";
    }

    @PostMapping
    public String createSubject(@RequestParam(name = "name") String name, @RequestParam(name = "teacher") Long teacherid , @RequestParam(name = "students") List<Long> studentsid) {
        User teacher = userService.getUser(teacherid);

        List<User> students = new ArrayList<>();
        for (Long id: studentsid
             ) {
            User student = userService.getUser(id);
            students.add(student);
        }
        Subject subject = subjectService.createSubject(name, teacher, students);
        return "redirect:/subjects/";
    }

/*
    @PostMapping
    public String createSubject(@ModelAttribute("subjectForm") @Valid SubjectForm subjectForm, BindingResult subjectBinding, Model model) {
        if (subjectBinding.hasErrors()){
            model.addAttribute("subjectForm", subjectForm);
            return "admin/createSubject";
        }
        subjectService.save(subjectFormConverter.update(new Subject(), subjectForm));

        return "redirect:/tasks/";
    }
*/

    @PostMapping(path = "/{id}/deleteSubject")
    public String deleteSubject(@PathVariable("id") Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return "redirect:/subjects";
    }


    @PostMapping(path = "/{id}/editSubject")
    public String editSubject(@PathVariable("id") Long subjectId) {
        subjectService.editSubject(subjectId);
        return "redirect:/subjects";
    }
    @PostMapping(path = "/{id}/abortEditSubject")
    public String abortEditSubject(@PathVariable("id") Long subjectId) {
        subjectService.abortEditSubject(subjectId);
        return "redirect:/subjects";
    }

    @PostMapping(path = "/{id}/saveNewSubject")
    public String saveNewSubject(@PathVariable("id") Long subjectId, @RequestParam(name = "newName")String newName,  @RequestParam(name = "newTeacher") User newTeacher) {
        subjectService.saveNewSubject(subjectId, newName, newTeacher);
        return "redirect:/subjects";
    }
}
