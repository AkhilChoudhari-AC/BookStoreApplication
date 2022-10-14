package com.example.bookstoreapplication.repository;


import com.example.bookstoreapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookStoreRepository extends JpaRepository<Book,Integer> {

    // Query to find data by bookName
    @Query(value = "select * from book where book_name= :bookName", nativeQuery = true)
    List<Book> findByBookName(String bookName);

    // Query to find data by authorName
    @Query(value = "select * from book where author_name= :authorName", nativeQuery = true)
    List<Book> findByBookAuthorName(String authorName);
    // Query to find data by totalPrice in ascending order
    @Query(value = "select * from book order by price", nativeQuery = true)
    List<Book> getSortedListOfBooksInAsc();

    // Query to find data by totalPrice in descending order
    @Query(value = "select * from book order by price desc", nativeQuery = true)
    List<Book> getSortedListOfBooksInDesc();

}