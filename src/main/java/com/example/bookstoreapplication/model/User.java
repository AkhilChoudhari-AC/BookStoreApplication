package com.example.bookstoreapplication.model;

import com.example.bookstoreapplication.dto.UserDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


// The @Entity annotation specifies that the class is an entity and is mapped to a database table

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;


    public User() {
    }

    public User(UserDTO dto){
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.address = dto.getAddress();
        this.password = dto.getPassword();
    }
    public User(Integer id,UserDTO userDTO){
        this.id = id;
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.password = userDTO.getPassword();
    }
}
