package com.kamilradzyminski.projekt.web;


import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.PersonRequest;
import com.kamilradzyminski.projekt.dto.SearchRequest;
import com.kamilradzyminski.projekt.dto.StatisticsResponse;
import com.kamilradzyminski.projekt.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class PersonController {

    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //TODO Strona główna
    @GetMapping
    public String homepage() {
        return "index";
    }

    //TODO Wyświetlenie wszystkich osób
    @GetMapping("/persons")
    public String persons(Model model) {
        List<Person> personList = personService.getAll();
        model.addAttribute("personsList", personList);
        return "persons";
    }

    //TODO Formularz dodawania osób
    @GetMapping("/persons/new")
    public String personForm(Model model) {
        model.addAttribute("person", new PersonRequest());
        return "personForm";
    }

    //TODO Formularz edycji osób
    @GetMapping("/persons/edit")
    public String personFormEdit(Model model) {
        model.addAttribute("person", new PersonRequest());
        return "personForm";
    }

    //TODO Obsługa dodania osoby z formularza
    @PostMapping("/persons/new")
    public String personFormSubmit(@ModelAttribute PersonRequest person) {
        return "redirect:/persons";
    }

    //TODO Obsługa edycji osoby z formularza
    @PostMapping("/persons/edit")
    public String personFormEditSubmit(@ModelAttribute PersonRequest person) {
        return "redirect:/persons";
    }

    //TODO Usuwanie osoby
    @GetMapping("/persons/delete/{id}")
    public String personDeleteUser(@PathVariable("id") int id) {
        return "redirect:/persons";
    }

    // TODO Wyszukiwarka po danym polu
    @GetMapping("/persons/search")
    public String searchPerson(Model model) {
        model.addAttribute("search", new SearchRequest());
        return "personSearch";
    }

    // TODO Statystyki osób
    @GetMapping("/persons/statistics")
    public String personStatistics(Model model) {
        model.addAttribute("statistics", new StatisticsResponse());
        return "personStatistics";
    }
}
