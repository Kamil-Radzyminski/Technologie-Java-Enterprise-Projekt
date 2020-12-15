package com.kamilradzyminski.projekt.dto;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsResponseTest {

    StatisticsResponse statisticsResponse;
    Validator validator;

    @Before
    public void setUp() {
        statisticsResponse = new StatisticsResponse();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void getCreditCardType() {
        statisticsResponse.setName("visa");
        assertEquals("visa",statisticsResponse.getName());
    }

    @Test
    public void getCount() {
        statisticsResponse.setCount(20);
        assertEquals(20,statisticsResponse.getCount());
    }
}
