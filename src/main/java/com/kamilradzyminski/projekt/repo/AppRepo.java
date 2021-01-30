package com.kamilradzyminski.projekt.repo;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppRepo extends JpaRepository<App, Long> {
    List<App> findAll();

    List<App> findAllByOrderByIdAsc();

    Optional<App> findById(Long id);

    void deleteById(Long id);

    App findByAppName(String appName);

    List<App> findAllByPersonListContains(Person person);
}
