package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.UserDTO;
import com.example.bookstoreapplication.model.User;

import java.util.List;

public interface IUserService {

    public String getWelcome();

    public User postDataToRepo(UserDTO userDTO);

    public List<User> getAllData();

    public User getDataById(Integer id);

    public User updateDataById(Integer id, UserDTO userDTO);

    public String deleteDataById(Integer id);

    public List<User> getDataByFirstName(String firstName);

}

