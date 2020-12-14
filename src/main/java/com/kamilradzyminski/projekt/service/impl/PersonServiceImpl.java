package com.kamilradzyminski.projekt.service.impl;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.types.PropertyType;
import com.kamilradzyminski.projekt.dto.PersonEditRequest;
import com.kamilradzyminski.projekt.dto.PersonRequest;
import com.kamilradzyminski.projekt.dto.StatisticsResponse;
import com.kamilradzyminski.projekt.service.PersonService;
import com.kamilradzyminski.projekt.utitles.CsvToXmlParser;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    ArrayList<Person> personList;
    final GenericApplicationContext context;

    public PersonServiceImpl(GenericApplicationContext context) {
        this.personList = new ArrayList<>();
        this.context = context;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBeansAfterStartup() {
        loadBeans();
    }

    public void loadBeans(){
        try {
            XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
            xmlReader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);
            xmlReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
            Map<String, Person> map = context.getBeansOfType(Person.class);
            map.values().stream().skip(1).forEach(person -> personList.add(person));
        } catch (Exception e){
            this.personList = CsvToXmlParser.loadList();
        }
    }

    // Zwracanie wszystkich osób
    @Override
    public List<Person> getAll() {
        return personList;
    }

    // Szukanie osób według atrybutu
    @Override
    public List<Person> getByProperty(PropertyType propertyType, String query) {
        List<Person> list;
        switch (propertyType) {
            case id:
                list = personList.stream().filter(p -> p.getId() == Integer.parseInt(query)).collect(Collectors.toList());
                break;
            case email:
                list = personList.stream().filter(p -> p.getEmail().startsWith(query)).collect(Collectors.toList());
                break;
            case gender:
                list = personList.stream().filter(p -> p.getGender().startsWith(query)).collect(Collectors.toList());
                break;
            case lastName:
                list = personList.stream().filter(p -> p.getLastName().startsWith(query)).collect(Collectors.toList());
                break;
            case firstName:
                list = personList.stream().filter(p -> p.getFirstName().startsWith(query)).collect(Collectors.toList());
                break;
            case creditCardType:
                list = personList.stream().filter(p -> p.getCreditCardType().startsWith(query)).collect(Collectors.toList());
                break;
            case creditCardNumber:
                list = personList.stream().filter(p -> p.getCreditCardNumber().startsWith(query)).collect(Collectors.toList());
                break;
            default:
                return Collections.emptyList();
        }
        return list;
    }

    // Tworzenie nowej osoby
    @Override
    public Person create(PersonRequest personRequest) {
        int index = personList.get(personList.size() - 1).getId() + 1;
        Person newPerson = new Person(index, personRequest.getFirstName(), personRequest.getLastName(), personRequest.getEmail(), personRequest.getGender(), personRequest.getCreditCardType(), personRequest.getCreditCardNumber());
        personList.add(newPerson);
        return newPerson;
    }

    // Edytowanie starej osoby
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

    // Usuwanie osób
    @Override
    public void delete(int id) {
        personList.stream().filter(person -> person.getId() == id).findFirst().ifPresent(personList::remove);
    }

    // TODO Zwracanie statystyk
    @Override
    public StatisticsResponse getStatistics() {
        return null;
    }

    // Zwracanie osoby po id
    @Override
    public Optional<Person> getById(int id) {
        return personList.stream().filter(person -> person.getId() == id).findFirst();
    }
}