package com.kamilradzyminski.projekt.domain;

import com.kamilradzyminski.projekt.domain.enums.Gender;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String creditCardType;
    private String creditCardNumber;

}

