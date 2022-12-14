package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.UserDTO;
import com.example.bookstoreapplication.model.User;

import java.util.List;

public interface IUserService {

    public String getWelcome();

    public User postDataToRepo(UserDTO userDTO);

    public List<User> getAllData();

    public User getDataById(Integer userId);

    public User updateDataById(Integer userId, UserDTO userDTO);

    public String deleteDataById(Integer userId);

    public List<User> getDataByFirstName(String firstName);

}

