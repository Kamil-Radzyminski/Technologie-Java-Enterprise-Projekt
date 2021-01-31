package com.kamilradzyminski.projekt.validation;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailValidation implements ConstraintValidator<ValidEmail, String> {
    @Autowired
    private PersonServiceImpl personService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Person person = this.personService.getPersonRepo().findByEmail(email);
        return person == null;
    }
}
