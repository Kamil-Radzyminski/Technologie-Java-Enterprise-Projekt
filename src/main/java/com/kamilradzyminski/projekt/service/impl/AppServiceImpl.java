package com.kamilradzyminski.projekt.service.impl;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.dto.Application;
import com.kamilradzyminski.projekt.repo.AppRepo;
import com.kamilradzyminski.projekt.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppRepo appRepo;


    public AppServiceImpl(AppRepo appRepo) {
        super();
        this.appRepo = appRepo;
    }

    @Override
    public App save(Application application) {
        App app = new App(application.getAppName(), application.getDomainName());

        return appRepo.save(app);
    }

    public App save(App app){
        return appRepo.save(app);
    }

    @Override
    public List<App> findAllApplications() {
        return appRepo.findAll();
    }

    @Override
    public Optional<App> findById(Long id) {
        return appRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
    appRepo.deleteById(id);
    }
}
