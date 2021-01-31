package com.kamilradzyminski.projekt.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.opencsv.bean.CsvBindByName;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Entity
@Table(name = "app")
public class App {
    @Id
    @CsvBindByName(column = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "app_name")
    @NotNull
    private String appName;

    @CsvBindByName(column = "domain_name")
    @NotNull
    private String domainName;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "persons_apps",
            joinColumns = @JoinColumn(
                    name = "app_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "person_id", referencedColumnName = "id"))
    Set<Person> personList = new HashSet<>();


    public App(String appName, String domainName) {
        this.appName = appName;
        this.domainName = domainName;
    }

    @Override
    public String toString() {
        return id +
                "," + appName +
                "," + domainName;
    }
}



