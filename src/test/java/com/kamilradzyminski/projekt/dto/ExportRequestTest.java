package com.kamilradzyminski.projekt.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExportRequestTest {

    ExportRequest exportRequest;
    Validator validator;

    @Before
    public void setUp(){
        exportRequest = new ExportRequest();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void getFilename() {
        exportRequest.setFilename("persons");
        assertEquals("persons",exportRequest.getFilename());
    }
}
