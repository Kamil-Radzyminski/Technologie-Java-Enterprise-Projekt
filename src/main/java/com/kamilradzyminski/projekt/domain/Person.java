package com.kamilradzyminski.projekt.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Person {
    @CsvBindByName(column = "id")
    private int id;

    @Size(min = 4, max = 32, message = "First name is invalid")
    @Pattern(regexp = "^[A-Za-z]*$", message = "First name is invalid")
    @CsvBindByName(column = "first_name")
    private String firstName;

    @Size(min = 4, max = 32, message = "Last name is invalid")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Last name is invalid")
    @CsvBindByName(column = "last_name")
    private String lastName;

    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", message = "Email is invalid")
    @CsvBindByName(column = "email")
    private String email;

    @NotEmpty(message = "Gender must be selected")
    @Pattern(regexp = "^(?:Male|Female)*$")
    @CsvBindByName(column = "gender")
    private String gender;

    @Size(min = 2, max = 32, message = "Credit card type is invalid")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Credit card type is invalid")
    @CsvBindByName(column = "credit card type")
    private String creditCardType;

    @Size(min = 4, max = 32, message = "Credit card number is invalid")
    @Pattern(regexp = "^[0-9]*$", message = "Credit card number is invalid")
    @CsvBindByName(column = "credit card number")
    private String creditCardNumber;
}


