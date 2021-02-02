package com.kamilradzyminski.projekt.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.opencsv.bean.CsvBindByName;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Entity
@Table(name = "persons", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    private String email;

    @NotNull
    private String country;

    @Size(min = 1, max = 32, message = "First name is invalid")
    @NotNull
    private String username;

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


