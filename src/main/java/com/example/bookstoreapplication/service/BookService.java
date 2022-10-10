package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.BookDTO;
import com.example.bookstoreapplication.exception.UserException;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.repository.BookStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j

// Created BookService class to serve api calls done by controller layer

public class BookService implements IBookService{


     // Autowired BookStoreRepository interface to inject its dependency here

    @Autowired
    BookStoreRepository bookStoreRepository;

    // Ability to save book details to repository
    @Override
    public Book createBook(BookDTO bookDTO) {
        Book newBook = new Book(bookDTO);
        return  bookStoreRepository.save(newBook);

    }


     // Ability to get book data by id

    @Override
    public Optional<Book> getBookDataById(int BookId) {
        Optional<Book> getBook=bookStoreRepository.findById(BookId);
        if(getBook.isEmpty()){
            throw new UserException("Book Store Details for id not found");
        }
        return getBook;

    }


    //Ability to get all book' data by findAll() method

    @Override
    public List<Book> getAllBookData() {
        List<Book> getBooks=bookStoreRepository.findAll();
        return getBooks;
    }

     //Ability to update book data for particular id
    @Override
    public Book updateRecordById(Integer BookId, BookDTO bookDTO) {

        Optional<Book> updateBook = bookStoreRepository.findById(BookId);
        if (updateBook.isEmpty()) {
            throw new UserException("Book record does not found");
        } else {
            Book updateUser = new Book(BookId, bookDTO);
            bookStoreRepository.save(updateUser);
            return updateUser;

        }
    }

    // ability to delete data by particular book id
    @Override
    public String deleteRecordById(int BookId) {
        Optional<Book> newBook = bookStoreRepository.findById(BookId);
        if (newBook.isEmpty()) {
            throw new UserException("Book record does not found");
        } else {
            bookStoreRepository.deleteById(BookId);
        }
        return "data deleted succesfull";
    }


     // ability to get Book data by particular book name

    @Override
    public List<Book> getBookByName(String bookName) {
        List<Book> findBook= bookStoreRepository.findByBookName(bookName);
        if(findBook.isEmpty()){
            throw new UserException(" Details for provided Book is not found");
        }
        return findBook;
    }


     //ability to get Book data by particular book Author name

    @Override
    public List<Book> getBookByAuthorName(String author){
        List<Book> findByAuthor = bookStoreRepository.findByBookAuthorName(author);
        if (findByAuthor.isEmpty()){
            throw new UserException("Author for this book is not available");
        }
        return findByAuthor;
    }


     // ability to sort the Books by its Price in Ascending Order

    @Override
    public List<Book> sortedListOfBooksInAscendingOrder() {
        List<Book> getSortedList=  bookStoreRepository.getSortedListOfBooksInAsc();
        return getSortedList;
    }

     // ability to sort the Books by its Price in Descending Order

    @Override
    public List<Book> sortedListOfBooksInDescendingOrder() {
        List<Book> getSortedListInDesc=  bookStoreRepository.getSortedListOfBooksInDesc();
        return getSortedListInDesc;
    }


}