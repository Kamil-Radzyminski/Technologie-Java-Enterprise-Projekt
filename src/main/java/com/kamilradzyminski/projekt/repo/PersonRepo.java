package com.kamilradzyminski.projekt.repo;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByEmail(String email);

    void deleteById(Long id);

    Person findByUsername(String username);

    List<Person> findAllByAppListContains(App app);

    List<Person> findAllByOrderByIdAsc();
}
