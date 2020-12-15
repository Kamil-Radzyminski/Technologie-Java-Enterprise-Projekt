package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.dto.types.PropertyType;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchRequestTest {

    SearchRequest searchRequest;
    Validator validator;

    @Before
    public void setUp() {
        searchRequest = new SearchRequest();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void getPropertyType() {
        searchRequest.setPropertyType(PropertyType.firstName);
        assertEquals(PropertyType.firstName, searchRequest.getPropertyType());
    }

    @Test
    public void getValue() {
        searchRequest.setValue("value");
        assertEquals("value",searchRequest.getValue());
    }
}
