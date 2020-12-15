package com.kamilradzyminski.projekt.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonRequestTest {

    PersonRequest personRequest;
    Validator validator;

    @Before
    public void setUp() {
        personRequest = new PersonRequest();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void getFirstName() {
        personRequest.setFirstName("Tulek");
        assertEquals("Tulek",personRequest.getFirstName());
    }

    @Test
    public void getLastName() {
        personRequest.setLastName("Jablonski");
        assertEquals("Jablonski",personRequest.getLastName());
    }

    @Test
    public void getEmail() {
        personRequest.setEmail("mail@mail.com");
        assertEquals("mail@mail.com",personRequest.getEmail());
    }

    @Test
    public void getGender() {
        personRequest.setGender("Male");
        assertEquals("Male", personRequest.getGender());
    }

    @Test
    public void getCreditCardType() {
        personRequest.setCreditCardType("visa");
        assertEquals("visa", personRequest.getCreditCardType());
    }

    @Test
    public void getCreditCardNumber() {
        personRequest.setCreditCardNumber("1234123123");
        assertEquals("1234123123", personRequest.getCreditCardNumber());
    }
}
