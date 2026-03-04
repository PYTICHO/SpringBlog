package com.thirdsteps.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");  // title: someParam
        return "home"; // название html файла в    resources/temlates
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "О нас");  // title: someParam
        return "about";
    }

    @GetMapping("/blog")
    public String blogMain(Model model) {
        model.addAttribute("title", "Статьи");
        return "blogMain";
    }
    
}
