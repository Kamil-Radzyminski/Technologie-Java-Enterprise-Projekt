package com.kamilradzyminski.projekt.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Person {
    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "first_name")
    private String firstName;
    @CsvBindByName(column = "last_name")
    private String lastName;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "gender")
    private String gender;
    @CsvBindByName(column = "credit card type")
    private String creditCardType;
    @CsvBindByName(column = "credit card number")
    private String creditCardNumber;

}

