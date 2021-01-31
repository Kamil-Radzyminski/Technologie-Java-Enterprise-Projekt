package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.validation.ValidEmail;
import com.kamilradzyminski.projekt.validation.ValidPassword;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class PersonRegister {
    @Pattern(regexp = "^[A-Z]+[A-z]*", message = "First name is invalid")
    private String firstName;
    @Pattern(regexp = "^[A-Z]+[A-z]*", message = "Last name is invalid")
    private String lastName;
    @ValidEmail
    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", message = "Email is invalid")
    private String email;
    @Pattern(regexp = "^[A-Z]+[A-z]*", message = "Country name is invalid")
    private String country;
    private String username;
    @ValidPassword
    private String password;

    public PersonRegister(String firstName, String lastName, String email, String country, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.password = password;
        this.username = username;
    }
}