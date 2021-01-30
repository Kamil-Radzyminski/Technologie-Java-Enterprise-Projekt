package com.kamilradzyminski.projekt.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kamilradzyminski.projekt.validation.ValidPassword;
import com.opencsv.bean.CsvBindByName;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Person {
    @Id
    @CsvBindByName(column = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Size(min = 1, max = 32, message = "First name is invalid")
//    @Pattern(regexp = "^[A-Za-z]*$", message = "First name is invalid")
    @CsvBindByName(column = "first_name")
    @NotNull
    @Column(name = "first_name")
    private String firstName;

//    @Size(min = 1, max = 32, message = "Last name is invalid")
//    @Pattern(regexp = "^[A-Za-z]*$", message = "Last name is invalid")
    @CsvBindByName(column = "last_name")
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", message = "Email is invalid")
    @CsvBindByName(column = "email")
    private String email;

//    @Size(min = 1, max = 32, message = "Country is invalid")
//    @Pattern(regexp = "^[A-Za-z]*$", message = "Country is invalid")
    @CsvBindByName(column = "country")
    @NotNull
    private String country;

    @Size(min = 1, max = 32, message = "First name is invalid")
    @CsvBindByName(column = "username")
    @NotNull
    private String username;

//    @Size(min = 1, max = 32, message = "password is invalid")
//    @CsvBindByName(column = "password")
//    @NotNull
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "haslo musi zawierac conajmniej 8 znaków" +
//            "w tym jedną lub więcej: małą literę, dużą literę oraz cyfrę")
    @ValidPassword
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "persons_roles",
            joinColumns = @JoinColumn(
                    name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @JsonBackReference
    @ManyToMany(mappedBy = "personList", fetch = FetchType.EAGER)
    Set<App> appList = new HashSet<>();


    public Person(String firstName, String lastName, String email, String country, String password, String username, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return id +
                "," + firstName +
                "," + lastName +
                "," + email +
                "," + country +
                "," + username;

    }

}


