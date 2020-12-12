package com.kamilradzyminski.projekt.service.impl;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.domain.enums.PropertyType;
import com.kamilradzyminski.projekt.dto.PersonEditRequest;
import com.kamilradzyminski.projekt.dto.PersonRequest;
import com.kamilradzyminski.projekt.dto.StatisticsResponse;
import com.kamilradzyminski.projekt.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    final ApplicationContext context;
    final ArrayList<Person> personList;

    public PersonServiceImpl(ApplicationContext context) {
        this.context = context;
        this.personList = new ArrayList<>();
    }

    // Zwracanie wszystkich osób
    @Override
    public List<Person> getAll() {
        return personList;
    }

    // TODO Szukanie według atrybutu
    @Override
    public List<Person> getByProperty(PropertyType propertyType, String query) {
        return null;
    }

    // TODO Tworzenie nowej osoby
    @Override
    public Person create(PersonRequest personRequest) {
        int index = personList.get(personList.size() - 1).getId() + 1;
        Person newPerson = new Person(index, personRequest.getFirstName(), personRequest.getLastName(), personRequest.getEmail(), personRequest.getGender(), personRequest.getCreditCardType(), personRequest.getCreditCardNumber());
        personList.add(newPerson);
        return newPerson;
    }

    // TODO Edytowanie starej osoby
    @Override
    public Optional<Person> update(int id, PersonEditRequest personRequest) {
        return personList.stream().filter(person -> person.getId() == id).peek(person -> {
            person.setFirstName(personRequest.getFirstName());
            person.setLastName(personRequest.getLastName());
            person.setEmail(personRequest.getEmail());
            person.setCreditCardNumber(personRequest.getCreditCardNumber());
            person.setCreditCardType(personRequest.getCreditCardType());
            person.setGender(personRequest.getGender());
        }).findFirst();
    }

    // TODO Usuwanie osób
    @Override
    public void delete(int id) {
        personList.stream().filter(person -> person.getId() == id).findFirst().ifPresent(personList::remove);
    }

    // TODO Zwracanie statystyk
    @Override
    public StatisticsResponse getStatistics() {
        return null;
    }

    // TODO Zwracanie osoby po id
    @Override
    public Optional<Person> getById(int id) {
        return null;
    }
}