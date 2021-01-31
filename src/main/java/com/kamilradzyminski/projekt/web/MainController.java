package com.kamilradzyminski.projekt.web;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.repo.AppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Configuration
public class MainController {
    @Autowired
    AppRepo appRepo;

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/")
    public String home() { return "index"; }

    @GetMapping("/guest/applications")
    public String apps(Model model) {
        List<App> apps = appRepo.findAll();
        model.addAttribute("applications", apps);
        return "GuestApplications";
    }
}
