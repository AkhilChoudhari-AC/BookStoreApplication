package com.example.bookstoreapplication.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


 // Data transfer object of User Model


@Data
public class UserDTO {
    @NotEmpty(message="First name cant be empty")
    @Pattern(regexp="^[a-zA-Z]{3,10}$",message="Please Enter correct first name")
    public String firstName;

    @NotEmpty(message="Last name cant be empty")
    @Pattern(regexp="^[a-zA-Z]{3,10}$",message="Please Enter correct last name")
    public String lastName;

    @Email
    private String email;

    @NotEmpty(message = "address can not be empty")
    private String address;

    @NotEmpty(message = "Password cant be empty")
    private String password;

    public UserDTO(){

    }

    public UserDTO(String firstName, String lastName, String email, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
