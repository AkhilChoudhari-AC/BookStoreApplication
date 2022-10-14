package com.example.bookstoreapplication.repository;

import com.example.bookstoreapplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface BookStoreCartRepository extends JpaRepository<Cart,Integer> {

    // Query to find data by bookId
    @Query(value="select * from cart where book_id =:bookId",nativeQuery =true)
    Optional<Cart> findByBookId(Integer bookId);
}