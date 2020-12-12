package com.kamilradzyminski.projekt.service.impl;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.domain.enums.PropertyType;
import com.kamilradzyminski.projekt.dto.PersonEditRequest;
import com.kamilradzyminski.projekt.dto.PersonRequest;
import com.kamilradzyminski.projekt.dto.StatisticsResponse;
import com.kamilradzyminski.projekt.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {


    final ArrayList<Person> personList;

    public PersonServiceImpl() {
        this.personList = new ArrayList<>();
    }

    // TODO Zwracanie wszystkich osób
    @Override
    public List<Person> getAll() {
        return null;
    }

    // TODO Szukanie według atrybutu
    @Override
    public List<Person> getByProperty(PropertyType propertyType, String query) {
        return null;
    }

    // TODO Tworzenie nowej osoby
    @Override
    public Person create(PersonRequest personRequest) {
        return null;
    }

    // TODO Edytowanie starej osoby
    @Override
    public Optional<Person> update(int id, PersonEditRequest personRequest) {
        return null;
    }

    // TODO Usuwanie osób
    @Override
    public void delete(int id) {

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