package de.hsba.bi.demo.web.user;

import de.hsba.bi.demo.subject.Subject;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/{id}/saveNewUser")
    public String saveNewUser(@PathVariable("id") Long userId, @RequestParam(name = "newName")String name,  @RequestParam(name = "newUsername")String username, @RequestParam(name = "newPassword")String password,  @RequestParam(name = "newRole")String role) {
        userService.saveNewUser(userId, name, username, password, role);
        return "redirect:/users";
    }

}
