package com.kamilradzyminski.projekt.dto;

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
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String username;
//    @NotNull
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "haslo musi zawierac conajmniej 8 znaków" +
//            "w tym jedną lub więcej: małą literę, dużą literę oraz cyfrę")
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