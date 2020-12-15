package com.kamilradzyminski.projekt.service.impl;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.PersonEditRequest;
import com.kamilradzyminski.projekt.dto.PersonRequest;
import com.kamilradzyminski.projekt.dto.StatisticsResponse;
import com.kamilradzyminski.projekt.dto.types.PropertyType;
import com.kamilradzyminski.projekt.dto.types.StatisticsType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {

    @Mock
    ApplicationContext applicationContext;

    @InjectMocks
    PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        personService.create(PersonRequest.builder().firstName("Jan").lastName("Kowalski").email("jan@kowalski.com").gender("Male").creditCardNumber("987654321").creditCardType("mastercard").build());
        personService.create(PersonRequest.builder().firstName("Adam").lastName("Nowak").email("adam@nowak.com").gender("Male").creditCardNumber("123456789").creditCardType("visa").build());
        personService.create(PersonRequest.builder().firstName("Karolina").lastName("Mazur").email("anna@piotrkowska.com").gender("Female").creditCardNumber("543219876").creditCardType("mastercard").build());
    }

    @Test
    public void create() {
        personService.create(PersonRequest.builder().firstName("Johny").lastName("Deep").email("johny@deep.com").gender("Male").creditCardNumber("678912345").creditCardType("visa").build());
        assertEquals(4, personService.getAll().size());
    }

    @Test
    public void getById() {
        Person person = personService.getById(2).get();
        assertEquals("Adam", person.getFirstName());
        assertEquals("Nowak", person.getLastName());
        assertEquals("adam@nowak.com", person.getEmail());
        assertEquals("123456789", person.getCreditCardNumber());
        assertEquals("visa", person.getCreditCardType());
    }

    @Test
    public void getByProperty() {
        List<Person> personList = personService.getByProperty(PropertyType.gender, "Male");
        assertEquals(2, personList.size());
    }

    @Test
    public void update() {
        Person person = personService.getById(3).get();
        person.setCreditCardType("visa");
        personService.update(3, new PersonEditRequest(person));
        Person updatedPerson = personService.getById(3).get();
        assertEquals("visa", updatedPerson.getCreditCardType());

    }

    @Test
    public void delete() {
        personService.delete(1);
        Optional<Person> person = personService.getById(1);
        assertFalse(person.isPresent());

    }

    @Test
    public void getStatistics() {
        List<StatisticsResponse> statisticsResponseList = personService.getStatistics(StatisticsType.creditCard);
        assertEquals(2, statisticsResponseList.size());
    }

    @Test
    public void importCsv() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("PersonOne_1_forTest.csv", new FileInputStream("src/main/resources/PersonOne_1_forTest.csv"));
        personService.importCsv(multipartFile);
        assertFalse(personService.personList.isEmpty());
    }

    @Test
    public void exportCsv() throws IOException {
        String path = "src/main/resources/PersonOne_1_forTest.csv";
        int size = personService.getAll().size();
        personService.exportCsv(path);
        personService.delete(1);
        MultipartFile multipartFile = new MockMultipartFile("PersonOne_1.csv", new FileInputStream(path));
        personService.importCsv(multipartFile);
        assertEquals(size, personService.getAll().size());
    }
}
