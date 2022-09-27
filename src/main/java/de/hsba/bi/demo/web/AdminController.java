package de.hsba.bi.demo.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {


    @GetMapping
    public String index(Model model) {

        return "admin/menu";
    }

}

