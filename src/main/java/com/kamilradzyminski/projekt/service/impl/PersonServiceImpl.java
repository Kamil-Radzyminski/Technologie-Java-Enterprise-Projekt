package com.kamilradzyminski.projekt.service.impl;

import com.kamilradzyminski.projekt.domain.Person;
import com.kamilradzyminski.projekt.domain.Role;
import com.kamilradzyminski.projekt.dto.PersonRegister;
import com.kamilradzyminski.projekt.repo.PersonRepo;
import com.kamilradzyminski.projekt.service.PersonService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;
@Getter
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepo personRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepo personRepo) {
        super();
        this.personRepo = personRepo;
    }

    @Override
    public Person save(PersonRegister personRegister) {
        Person person = new Person(personRegister.getFirstName(),
                personRegister.getLastName(),
                personRegister.getEmail(),
                personRegister.getCountry(),
                passwordEncoder.encode(personRegister.getPassword()),
                personRegister.getUsername(),
                Arrays.asList(new Role("ROLE_USER")));
        return personRepo.save(person);
    }

    @Override
    public Person saveAdmin(PersonRegister personRegister) {
        Person person = new Person(personRegister.getFirstName(),
                personRegister.getLastName(),
                personRegister.getEmail(),
                personRegister.getCountry(),
                passwordEncoder.encode(personRegister.getPassword()),
                personRegister.getUsername(),
                Arrays.asList(new Role("ROLE_ADMIN")));
        return personRepo.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = personRepo.findByEmail(username);
        if (person == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(), mapRolesToAuthorities(person.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}