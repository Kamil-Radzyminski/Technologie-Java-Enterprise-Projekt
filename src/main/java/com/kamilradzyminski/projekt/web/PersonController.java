package com.kamilradzyminski.projekt.web;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.*;
import com.kamilradzyminski.projekt.exceptions.NotFoundException;
import com.kamilradzyminski.projekt.repo.AppRepo;
import com.kamilradzyminski.projekt.repo.PersonRepo;
import com.kamilradzyminski.projekt.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PersonController {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private AppRepo appRepo;
    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private List<Person> persons = new ArrayList<>();

    @GetMapping("/accdelete")
    public String accountDelete() {
        return "AccDelete";
    }

    @RequestMapping(value = "/accdelete", method = RequestMethod.POST)
    public String DeleteAccount(Principal principal) {
        personRepo.deleteById(personRepo.findByEmail(principal.getName()).getId());
        return "login";
    }

    @GetMapping("/accupdate")
    public String applicationUpdate(@Valid Model model, Principal principal) {
        Person personToUpdate = personRepo.findById(personRepo.findByEmail(principal.getName())
                .getId()).orElseThrow(() -> new NotFoundException(personRepo.findByEmail(principal.getName())
                .getId()));
        model.addAttribute("user", personToUpdate);
        return "AccEdit";
    }

    @RequestMapping(value = "/accupdate", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") @Valid PersonRegister NewPerson, Principal principal) {
        personRepo.findById(personRepo.findByEmail(principal.getName()).getId())
                .map(person -> {
                    person.setFirstName(NewPerson.getFirstName());
                    person.setLastName(NewPerson.getLastName());
                    person.setEmail(NewPerson.getEmail());
                    person.setCountry(NewPerson.getCountry());
                    person.setUsername(NewPerson.getUsername());
                    person.setPassword(passwordEncoder.encode(NewPerson.getPassword()));
                    personRepo.save(person);
                    return "redirect:/logout";
                })
                .orElseGet(() -> "redirect:/logout");
        return "redirect:/logout";
    }

    @GetMapping("/myacc")
    public String Myacc() {
        return "MyAcc";
    }

    @GetMapping("/allacc")
    public String application(@Valid Model model) {
        persons = personRepo.findAllByOrderByIdAsc();
        model.addAttribute("persons", persons);
        return "AllAcc";
    }

    @GetMapping("/accfromapp")
    public String GetIdtoShow() {
        return "FindPersonsFromApp";
    }

    @RequestMapping(value = "/accfromapp", method = RequestMethod.POST)
    public String ShowPersonsFromApp(@RequestParam Long id, @Valid Model model) {
        model.addAttribute("persons",personRepo.findAllByAppListContains(appRepo.findById(id).orElseThrow(() -> new NotFoundException(id))));

        return "AllAcc";
    }

    @GetMapping("/accdelete/{id}")
    public String accountDeleteId(@PathVariable Long id) {
        Person personToDelete = personRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        personRepo.deleteById(personToDelete.getId());
        return "redirect:/allacc";
    }

    @GetMapping("/accedit/{id}")
    public String accountEditId(@PathVariable Long id, Model model) {
        Person personToFind = personRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        model.addAttribute("person", personToFind);
        return "AccIdEdit";
    }

    @PostMapping("/accedit/id")
    public String updateUserId(@ModelAttribute("person") @Valid PersonRegister NewPerson, Principal principal) {
        personRepo.findById(personRepo.findByEmail(principal.getName()).getId())
                .map(person -> {
                    person.setFirstName(NewPerson.getFirstName());
                    person.setLastName(NewPerson.getLastName());
                    person.setEmail(NewPerson.getEmail());
                    person.setCountry(NewPerson.getCountry());
                    person.setUsername(NewPerson.getUsername());
                    person.setPassword(passwordEncoder.encode(NewPerson.getPassword()));
                    personRepo.save(person);
                    return "redirect:/allacc";
                })
                .orElseGet(() -> "redirect:/allacc");
        return "redirect:/allacc";
    }

    @PostMapping("/allacc/tocsv")
    public String accountsToCsv() throws IOException {
        String path = System.getProperty("person.home") + File.separator + "Downloads" + File.separator + "persons.csv";
        FileWriter f = new FileWriter(path);
        for (Person person : persons) {
            f.write("\n" + person.toString());
        }
        f.close();
        return "redirect:/allacc";
    }

}
