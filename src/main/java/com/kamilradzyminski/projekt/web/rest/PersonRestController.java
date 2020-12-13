package com.kamilradzyminski.projekt.web.rest;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.PersonEditRequest;
import com.kamilradzyminski.projekt.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonRestController {

    final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    // Wyświetlanie danej osoby w postaci JSON'a
    @CrossOrigin
    @GetMapping(value = "/api/persons/{id}")
    public ResponseEntity<Person> getNoteById(@PathVariable int id){
        Optional<Person> optionalPerson = personService.getById(id);
        return optionalPerson.map(person -> new ResponseEntity<>(person, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
