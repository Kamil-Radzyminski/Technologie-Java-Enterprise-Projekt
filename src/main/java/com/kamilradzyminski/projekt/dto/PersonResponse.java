package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.domain.enums.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String creditCardType;
    private String creditCardNumber;

}
