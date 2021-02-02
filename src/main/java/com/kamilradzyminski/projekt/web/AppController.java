package com.kamilradzyminski.projekt.web;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.Application;
import com.kamilradzyminski.projekt.exceptions.NotFoundException;
import com.kamilradzyminski.projekt.repo.AppRepo;
import com.kamilradzyminski.projekt.repo.PersonRepo;
import com.kamilradzyminski.projekt.service.impl.AppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private AppServiceImpl appService;
    private List<App> apps = new ArrayList<>();
    @Autowired
    private AppRepo appRepo;
    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/application")
    public String application(Model model, Principal principal) {
        Person personToFind = personRepo.findByEmail(principal.getName());
        apps = appRepo.findAllByPersonListContains(personToFind);
        model.addAttribute("apps", apps);
        return "Applications";
    }

    @GetMapping("/application/all")
    public String allApplication(@Valid Model model) {
        apps = appRepo.findAllByOrderByIdAsc();
        model.addAttribute("apps", apps);
        return "AllApplications";
    }

    @GetMapping("/application/add")
    public String AddingApplication(@Valid Model model) {
        model.addAttribute("app", new App());
        return "ApplicationForm";
    }

    @ModelAttribute("application")
    public Application application() {
        return new Application();
    }

    @PostMapping("/application/add")
    public String AddApplication(@ModelAttribute("app") @Valid Application application, Principal principal) {
        App app = new App(application.getAppName(), application.getDomainName());
        app.getPersonList().add(personRepo.findByEmail(principal.getName()));
        appRepo.save(app);
        return "redirect:/application";
    }

    @GetMapping("/application/delete")
    public String DeletingApplication() {
        return "ApplicationDeleteForm";
    }

    @RequestMapping(value = "/application/delete", method = RequestMethod.POST)
    public String DeleteApplication(@RequestParam Long id) {
        App app = appRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        app.getPersonList().clear();
        appRepo.deleteById(id);
        return "redirect:/application";
    }

    @GetMapping("/application/update")
    public String UpdatingApplication(@Valid Model model) {
        model.addAttribute("app", new App());
        return "ApplicationUpdateForm";
    }


    @RequestMapping(value = "/application/update", method = RequestMethod.POST)
    public String updateApplication(@ModelAttribute("app") @Valid Application NewApp) {
        appService.findById(NewApp.getId())
                .map(app -> {
                    app.setAppName(NewApp.getAppName());
                    app.setDomainName(NewApp.getDomainName());
                    System.out.println(NewApp.getId());
                    appService.save(app);
                    return "redirect:/application";
                })
                .orElseGet(() -> "redirect:/application");
        return "redirect:/application";
    }

    @GetMapping("/application/update/{id}")
    public String UpdatingApplicationId(@Valid Model model, @PathVariable Long id) {
        App app = new App();
        app.setId(id);
        model.addAttribute("app", app);
        return "ApplicationIdUpdateForm";
    }

    @GetMapping("/application/add/{id}")
    public String AddingApplicationId(@PathVariable Long id, Principal principal) {
        App app = appRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        app.getPersonList().add(personRepo.findByEmail(principal.getName()));
        appRepo.save(app);
        return "redirect:/application";
    }

    @RequestMapping(value = "/application/idupdate", method = RequestMethod.POST)
    public String updateApplicationById(@ModelAttribute("app") @Valid Application NewApp) {
        appService.findById(NewApp.getId())
                .map(app -> {
                    app.setAppName(NewApp.getAppName());
                    app.setDomainName(NewApp.getDomainName());
                    appService.save(app);
                    return "redirect:/application";
                })
                .orElseGet(() -> "redirect:/application");
        return "redirect:/application";
    }

    @GetMapping("/application/delete/{id}")
    public String DeleteApplicationId(@PathVariable Long id) {
        App app = appRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        app.getPersonList().clear();
        appRepo.deleteById(id);
        return "redirect:/application";
    }


    @GetMapping("/application/{id}/users")
    public String ShowAppUsers(@PathVariable Long id, Model model) {
        App app = appRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        List<Person> persons = personRepo.findAllByAppListContains(app);
        model.addAttribute("persons", persons);
        return "AllAcc";
    }

    @PostMapping("/apps/tocsv")
    public String appsToCsv() throws IOException {
        String path = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "apps.csv";
        FileWriter f = new FileWriter(path);
        for (App app : apps) {
            f.write("\n" + app.toString());
        }
        f.close();
        return "redirect:/application";
    }
}
