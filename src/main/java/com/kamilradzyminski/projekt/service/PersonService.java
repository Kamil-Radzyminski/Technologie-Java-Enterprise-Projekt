package com.kamilradzyminski.projekt.service;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.PersonRequest;
import com.kamilradzyminski.projekt.dto.StatisticsResponse;
import com.kamilradzyminski.projekt.dto.PersonEditRequest;
import com.kamilradzyminski.projekt.dto.types.PropertyType;
import com.kamilradzyminski.projekt.dto.types.StatisticsType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAll();

    List<Person> getByProperty(PropertyType propertyType, String query);

    Person create(PersonRequest personRequest);

    Optional<Person> update(int id, PersonEditRequest personRequest);

    void delete(int id);

    Optional<Person> getById(int id);

    List<StatisticsResponse> getStatistics(StatisticsType statisticsType);

    void importCsv(MultipartFile file);

    void exportCsv(String path);
}