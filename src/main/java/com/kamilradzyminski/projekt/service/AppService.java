package com.kamilradzyminski.projekt.service;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.dto.Application;

import java.util.List;
import java.util.Optional;

public interface AppService {
    App save(Application application);
    List<App> findAllApplications();
    Optional<App> findById(Long id);
    void deleteById(Long id);
}
