package com.kamilradzyminski.projekt.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonServiceImplBeansTest {

    @Autowired
    PersonServiceImpl personService;

    @Test
    public void loadBeansAfterStartup() {
        int size = personService.getAll().size();
        assertEquals(200, size);
    }
}
