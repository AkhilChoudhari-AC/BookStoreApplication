package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.UserDTO;
import com.example.bookstoreapplication.exception.UserException;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



    @Slf4j
    @Service
    public class UserService implements IUserService {
        //dependence injection, it is a class level injection
        @Autowired
        UserRepository repository;


        public String getWelcome() {
            return "Welcome to BookStore Application Program !!!";
        }

        public User postDataToRepo(UserDTO userDTO) {
            User newUser = new User(userDTO);
            repository.save(newUser);
            return newUser;
        }

        public List<User> getAllData() {
            List<User> list = repository.findAll();
            return list;
        }

        public User getDataById(Integer id) {
            Optional<User> newUser = repository.findById(id);
            if (newUser.isPresent()) {
                return newUser.get();
            } else throw new UserException("Book id not found");
        }


        public User updateDataById(Integer id, UserDTO userDTO) {
            Optional<User> newUser = repository.findById(id);
            if (newUser.isPresent()) {
                User user = new User(id, userDTO);
                repository.save(user);
                return user;
            } else {
                throw new UserException("Book Not found");
            }
        }

        public String deleteDataById(Integer id) {
            Optional<User> newUser = repository.findById(id);
            if (newUser.isPresent()) {
                repository.deleteById(id);
            } else {
                throw new UserException("Book Details not found");
            }
            return null;
        }


        public List<User> getDataByFirstName(String firstName) {
            List<User> newUse = repository.findByFirstName(firstName);
            if (newUse.isEmpty()) {
                throw new UserException("Book Not Found");
            }
            return newUse;
        }

    }
