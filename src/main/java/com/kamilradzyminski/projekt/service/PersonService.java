package com.kamilradzyminski.projekt.service;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.PersonRequest;
import com.kamilradzyminski.projekt.dto.StatisticsResponse;
import com.kamilradzyminski.projekt.domain.enums.PropertyType;

import java.util.List;

public interface PersonService {
    List<Person> getAll();
    List<Person> getByProperty(PropertyType propertyType, String query);
    Person create(PersonRequest personRequest);
    Person update(int id, PersonRequest personRequest);
    void delete(int id);
    StatisticsResponse getStatistics();
}