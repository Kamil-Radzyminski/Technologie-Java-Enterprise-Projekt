package com.kamilradzyminski.projekt.web;

import com.kamilradzyminski.projekt.dto.PersonRegister;
import com.kamilradzyminski.projekt.service.SendEmailService;
import com.kamilradzyminski.projekt.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class PersonRegisterController {

    @Autowired
    SendEmailService sendEmailService;

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
    public String registerPersonAccount(@ModelAttribute("person") @Valid PersonRegister personRegister, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        personService.save(personRegister);
        sendEmailService.sendEmail(personRegister.getEmail(), ("Welcome in my app " + personRegister.getUsername() + ", you are registered now!"), "Registration");
        return "redirect:/registration?success";
    }
}

