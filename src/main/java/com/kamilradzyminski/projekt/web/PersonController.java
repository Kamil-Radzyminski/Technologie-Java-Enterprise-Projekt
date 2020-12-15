package com.kamilradzyminski.projekt.web;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.*;
import com.kamilradzyminski.projekt.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Controller
public class PersonController {

    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //Strona główna
    @GetMapping
    public String homepage() {
        return "index";
    }

    //Wyświetlenie wszystkich osób
    @GetMapping("/persons")
    public String persons(Model model) {
        List<Person> personList = personService.getAll();
        model.addAttribute("personsList", personList);
        return "persons";
    }

    //Formularz dodawania osób
    @GetMapping("/persons/new")
    public String personForm(Model model) {
        PersonRequest personRequest = new PersonRequest();
        model.addAttribute("newPerson", personRequest);
        return "personForm";
    }

    //Formularz edycji osób
    @GetMapping("/persons/edit/{id}")
    public String personFormEdit(@PathVariable("id") int id, Model model) {
        Optional<Person> optionalPerson = personService.getById(id);
        if(optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            PersonEditRequest oldPerson = new PersonEditRequest(person);
            model.addAttribute("oldPerson", oldPerson);
            return "personFormEdit";
        } else {
            return "404";
        }
    }

    // Obsługa dodania osoby z formularza
    @PostMapping("/persons/new")
    public String personFormSubmit(@Valid @ModelAttribute("newPerson") PersonRequest personRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "personForm";
        }
        personService.create(personRequest);
        return "redirect:/persons";
    }

    // Obsługa edycji osoby z formularza
    @PostMapping("/persons/edit/{id}")
    public String personFormEditSubmit(@PathVariable("id") int id, @Valid @ModelAttribute("oldPerson") PersonEditRequest person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "personFormEdit";
        }
        personService.update(id, person);
        return "redirect:/persons";
    }

    //Usuwanie osoby
    @GetMapping("/persons/remove/{id}")
    public String personRemoveUser(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/persons";
    }

    //Wyszukiwarka osoby po danym polu
    @GetMapping("/persons/search")
    public String searchPerson(Model model) {
        model.addAttribute("search", new SearchRequest());
        return "personSearch";
    }

    // Obsługa wyszukiwarki
    @PostMapping("/persons/search")
    public String searchPersonResult(Model model, @ModelAttribute SearchRequest searchRequest) {
        List<Person> personList = personService.getByProperty(searchRequest.getPropertyType(), searchRequest.getValue());
        model.addAttribute("personsList", personList);
        return "personSearchResult";
    }

    // Statystyki osób
    @GetMapping("/persons/statistics")
    public String personStatistics() {
        return "personStatistics";
    }

    // Statystyki osób - płeć
    @GetMapping("/persons/genderStatistics")
    public String personGenderStatistics() {
        return "personGenderStatistics";
    }

    // Ustawienia
    @GetMapping("/settings")
    public String settings(Model model) {
        ExportRequest exportRequest = new ExportRequest();
        model.addAttribute("exportRequest", exportRequest);
        return "settings";
    }

    // Importowanie osób z pliku .csv
    @PostMapping("/settings/sendCsv")
    public String settingsSendCsv(@RequestParam("file") MultipartFile file) {
        personService.importCsv(file);
        return "redirect:/persons";
    }

    // Exportowanie osób do pliku .csv
    @PostMapping("/settings/downloadCsv")
    public void settingsDownloadCsv(@ModelAttribute ExportRequest exportRequest, HttpServletResponse response) {
        String path = "src/main/resources/PersonOne_1.csv";
        personService.exportCsv(path);

        Path file = Paths.get(path);
        if (Files.exists(file)) {
            response.setContentType("application/fileCsv");
            response.addHeader("Content-Disposition", "attachment; filename=" + exportRequest.getFilename() + ".csv");
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                System.out.println("Error - " + e.getMessage());
            }
        } else {
            System.out.println("File not found");
        }
    }
}
