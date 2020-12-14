package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.domain.Person;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PersonRequest {
    @Size(min = 4, max = 32, message = "First name is invalid")
    @Pattern(regexp = "^[A-Za-z]*$", message = "First name is invalid")
    private String firstName;

    @Size(min = 4, max = 32, message = "Last name is invalid")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Last name is invalid")
    private String lastName;

    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", message = "Email is invalid")
    private String email;

    @NotEmpty(message = "Gender must be selected")
    @Pattern(regexp = "^(?:Male|Female)*$")
    private String gender;

    @Size(min = 2, max = 32, message = "Credit card type is invalid")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Credit card type is invalid")
    private String creditCardType;

    @Size(min = 4, max = 32, message = "Credit card number is invalid")
    @Pattern(regexp = "^[0-9]*$", message = "Credit card number is invalid")
    private String creditCardNumber;

    public PersonRequest(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.gender = person.getGender();
        this.creditCardNumber = person.getCreditCardNumber();
        this.creditCardType = person.getCreditCardType();
    }
}
