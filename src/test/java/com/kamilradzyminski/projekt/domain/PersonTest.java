package com.kamilradzyminski.projekt.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonTest {

    Person person;
    Validator validator;


    @Before
    public void setUp(){
        person = new Person();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void getId() {
        person.setId(10);
        assertEquals(10, person.getId());
    }

    @Test
    public void testValidator() {
        person.setFirstName("Tulek");
        person.setLastName("Jablonowski&*@#");
        person.setGender("Male");
        person.setEmail("admin@gmail");
        person.setCreditCardNumber("qwertyu");
        person.setCreditCardType("123456");

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        assertThat(violations.size()).isEqualTo(4);
    }
}