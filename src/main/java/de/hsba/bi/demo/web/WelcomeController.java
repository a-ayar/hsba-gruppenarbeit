package de.hsba.bi.demo.web;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@PreAuthorize("hasAnyRole('SCHÜLER','LEHRER')")
@Controller
@RequestMapping("/welcome")
@RequiredArgsConstructor
public class WelcomeController {


    @GetMapping
    public String index(Model model) {

        return "tasks/welcome";
    }

}

