package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.BookDTO;
import com.example.bookstoreapplication.model.Book;

import java.util.List;
import java.util.Optional;


 // Created IBookService interface to achieve abstraction

public interface IBookService {
    Book createBook(BookDTO bookDTO);

    Optional<Book> getBookDataById(Integer bookId);

    List<Book> getAllBookData();

    Book updateRecordById(Integer bookId, BookDTO bookDTO);

    Object deleteRecordById(Integer bookId);

    List<Book> getBookByName(String bookName);

    List<Book> getBookByAuthorName(String authorName);

    List<Book> sortedListOfBooksInAscendingOrder();

    List<Book> sortedListOfBooksInDescendingOrder();


}