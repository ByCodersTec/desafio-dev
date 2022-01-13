package br.com.samaelSimoes.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileIndexController {
    @GetMapping
    public String get(Model model) {
        return "index";
    }
}
