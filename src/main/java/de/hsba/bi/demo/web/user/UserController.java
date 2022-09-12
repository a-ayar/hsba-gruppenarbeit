package de.hsba.bi.demo.web.user;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserService;
import de.hsba.bi.demo.web.task.TaskForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserFormConverter userFormConverter;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("userForm",new UserForm());
        return "admin/showUser";
    }
    @ModelAttribute("users")
    public List<User> getUsers(){
        return userService.findAll();
    }

    @PostMapping
    public String create(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult userBinding, Model model) {
        if (userBinding.hasErrors()){
            model.addAttribute("userForm", userForm);
            return "/admin/showUser";
        }
        userService.save(userFormConverter.update(new User(), userForm));

        return "redirect:/users/";
    }

    @PostMapping(path = "/{id}/deleteUser")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping(path = "/{id}/editUser")
    public String editUser(@PathVariable("id") Long userId) {
        userService.editUser(userId);
        return "redirect:/users/";
    }
    @PostMapping(path = "/{id}/abortEditUser")
    public String abortEditUser(@PathVariable("id") Long userId) {
        userService.abortEditUser(userId);
        return "redirect:/users/";
    }

    @PostMapping(path = "/{id}/saveNewUser")
    public String saveNewUser(@PathVariable("id") Long userId, @ModelAttribute("userForm") @Valid UserForm userForm, BindingResult userBinding, Model model) {
        if (userBinding.hasErrors()){
            model.addAttribute("userForm", userForm);
            return "admin/showUser";
        }
        User user = userFormConverter.update(userService.getUser(userId), userForm);
        userService.save(user);
        return "redirect:/users";
    }


}
