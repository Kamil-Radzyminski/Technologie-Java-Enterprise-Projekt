package com.kamilradzyminski.projekt.web.api;

import com.kamilradzyminski.projekt.domain.App;
import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.PersonRegister;
import com.kamilradzyminski.projekt.exceptions.NotFoundException;
import com.kamilradzyminski.projekt.repo.PersonRepo;
import com.kamilradzyminski.projekt.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonRestController {
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public PersonRestController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @GetMapping("/api/persons")
    List<Person> all() {
        return personRepo.findAll();
    }

    @Transactional
    @PostMapping("/api/persons")
    Person addUser(@RequestBody @Valid PersonRegister newPerson) {
        return personService.save(newPerson);
    }

    @GetMapping("/api/persons/{id}")
    Person one(@PathVariable Long id) {
        return personRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @GetMapping("/api/persons/frompolandwithapps")
    List<Person> FromPolandWithApps() {
        return personRepo
                .findAll()
                .stream()
                .filter(c -> c.getAppList().size() > 0 && c.getCountry().equals("Polska"))
                .collect(Collectors.toList());
    }

    @PutMapping("/api/persons/{id}")
    Person updateUser(@RequestBody @Valid PersonRegister newPerson, @PathVariable Long id) {
        return personRepo.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setEmail(newPerson.getEmail());
                    person.setCountry(newPerson.getCountry());
                    person.setUsername(newPerson.getUsername());
                    person.setPassword(passwordEncoder.encode(newPerson.getPassword()));
                    return personRepo.save(person);
                })
                .orElseThrow(() -> new NotFoundException(id));
    }

    @DeleteMapping("/api/persons/{id}")
    void deleteUser(@PathVariable Long id) {
        Person personToDelete = personRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        for (App app : personToDelete.getAppList()) {
            app.getPersonList().remove(personToDelete);
        }
        personRepo.deleteById(id);
    }
}
