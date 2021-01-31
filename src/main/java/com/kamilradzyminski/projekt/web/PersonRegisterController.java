package com.kamilradzyminski.projekt.web;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.PersonRegister;
import com.kamilradzyminski.projekt.service.impl.PersonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class PersonRegisterController {

    private PersonServiceImpl personService;

    public PersonRegisterController(PersonServiceImpl personService){
        super();
        this.personService = personService;
    }

    @ModelAttribute("person")
    public PersonRegister personRegister() {
        return new PersonRegister();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerPersonAccount(@ModelAttribute("person") @Valid PersonRegister personRegister){
        personService.save(personRegister);
//email sender
        return "redirect:/registration?success";
    }
}

