package com.kamilradzyminski.projekt.web.rest;

import com.kamilradzyminski.projekt.dto.PersonEditRequest;
import com.kamilradzyminski.projekt.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController {

    final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    // TODO Wyświetlanie danej osoby w postaci JSON'a
    @CrossOrigin
    @GetMapping(value = "/api/persons/{id}")
    public ResponseEntity<PersonEditRequest> getNoteById(@PathVariable String id){
        return new ResponseEntity<>(new PersonEditRequest(), HttpStatus.OK);
    }

}
