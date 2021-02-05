package com.kamilradzyminski.projekt.web.api;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.Application;
import com.kamilradzyminski.projekt.exceptions.NotFoundException;
import com.kamilradzyminski.projekt.repo.AppRepo;
import com.kamilradzyminski.projekt.repo.PersonRepo;
import com.kamilradzyminski.projekt.service.impl.AppServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppRestController {
    @Autowired
    private AppRepo appRepo;
    @Autowired
    private AppServiceImpl appService;
    @Autowired
    PersonRepo personRepo;

    @GetMapping("/api/apps")
    List<App> all() {
        return appRepo.findAll();
    }

    @PostMapping("/api/apps")
    App addApplication(@RequestBody Application application) {
        return appService.save(application);
    }

    @GetMapping("/api/apps/{id}")
    App one(@PathVariable Long id) {
        return appRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/api/apps/{id}")
    App updateApp(@RequestBody Application newApp, @PathVariable Long id) {
        return appRepo.findById(id)
                .map(app -> {
                    app.setAppName(newApp.getAppName());
                    app.setDomainName(newApp.getDomainName());
                    return appService.save(app);
                })
                .orElseThrow(() -> new NotFoundException(id));
    }

    @DeleteMapping("/api/apps/{id}")
    void deleteApp(@PathVariable Long id) {
        App app = appRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        app.getPersonList().clear();
        appService.deleteById(id);
    }
}
