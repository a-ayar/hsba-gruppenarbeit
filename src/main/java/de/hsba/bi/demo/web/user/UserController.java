package de.hsba.bi.demo.web.user;

import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
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
}
