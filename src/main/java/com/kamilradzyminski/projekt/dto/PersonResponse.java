package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.domain.enums.Gender;

public class PersonResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String creditCardType;
    private String creditCardNumber;

    public PersonResponse() {
    }

    public PersonResponse(int id, String firstName, String lastName, String email, Gender gender, String creditCardType, String creditCardNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
