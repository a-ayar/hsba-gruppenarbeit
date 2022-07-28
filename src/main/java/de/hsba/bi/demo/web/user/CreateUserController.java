package de.hsba.bi.demo.web.user;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class CreateUserController {

    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/createUser";
    }

    @PostMapping
    public String create(String name, String username,String password, String role) {
        User user = userService.createUser(name, username, password, role);
        return "redirect:/users/";
    }
}
