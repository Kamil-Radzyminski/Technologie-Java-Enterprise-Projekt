package com.kamilradzyminski.projekt.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonEditRequestTest {

    PersonEditRequest personEditRequest;
    Validator validator;

    @Before
    public void setUp() {
        personEditRequest = new PersonEditRequest();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void getId(){
        personEditRequest.setId(10);
        assertEquals(10,personEditRequest.getId());
    }

    @Test
    public void getFirstName() {
        personEditRequest.setFirstName("Tulek");
        assertEquals("Tulek",personEditRequest.getFirstName());
    }

    @Test
    public void getLastName() {
        personEditRequest.setLastName("Jablonski");
        assertEquals("Jablonski",personEditRequest.getLastName());
    }

    @Test
    public void getEmail() {
        personEditRequest.setEmail("mail@mail.com");
        assertEquals("mail@mail.com",personEditRequest.getEmail());
    }

    @Test
    public void getGender() {
        personEditRequest.setGender("Male");
        assertEquals("Male", personEditRequest.getGender());
    }

    @Test
    public void getCreditCardType() {
        personEditRequest.setCreditCardType("visa");
        assertEquals("visa", personEditRequest.getCreditCardType());
    }

    @Test
    public void getCreditCardNumber() {
        personEditRequest.setCreditCardNumber("1234123123");
        assertEquals("1234123123", personEditRequest.getCreditCardNumber());
    }
}
