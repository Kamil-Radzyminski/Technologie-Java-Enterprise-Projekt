package com.kamilradzyminski.projekt.service;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.dto.PersonRegister;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface PersonService extends UserDetailsService {
    Person save(PersonRegister PersonRegister);

    Person saveAdmin(PersonRegister PersonRegister);
}