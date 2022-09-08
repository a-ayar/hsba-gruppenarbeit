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

@Controller
@RequestMapping("/users/createUser")
@RequiredArgsConstructor
public class CreateUserController {

    private final UserService userService;
    private final UserFormConverter formConverter;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/createUser";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String create(Model model, String name, String username, String password, String role, @ModelAttribute("userForm")
    @Valid UserForm userForm, BindingResult userBinding) {
        User user = userService.createUser(name, username, password, role);
        if (userBinding.hasErrors()) {
            model.addAttribute("userForm", formConverter.toForm(user));
            return "users/";
        }


        return "redirect:/users/";
    }

}
