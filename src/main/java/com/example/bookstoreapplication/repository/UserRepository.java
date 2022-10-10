package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {



    // Query to find data by firstname
    @Query(value="select * from user where first_name= :firstName",nativeQuery = true)
    List<User> findByFirstName(String firstName);

}

