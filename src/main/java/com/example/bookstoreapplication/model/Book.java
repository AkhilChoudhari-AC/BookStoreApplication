package com.example.bookstoreapplication.model;

import com.example.bookstoreapplication.dto.BookDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// The @Entity annotation specifies that the class is an entity and is mapped to a database table
@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String  bookName;
    private String  authorName;
    private String  bookDescription;
    private String  bookImage;
    private Integer price;
    private Integer quantity;

    public Book(BookDTO bookDTO){
        this.authorName=bookDTO.getAuthorName();
        this.bookDescription=bookDTO.getBookDescription();
        this.bookImage=bookDTO.getBookImage();
        this.price=bookDTO.getPrice();
        this.quantity=bookDTO.getQuantity();
        this.bookName=bookDTO.getBookName();
    }

    public Book() {
    }

    public Book(Integer bookId, BookDTO bookDTO){
        this.bookId=bookId;
        this.bookName=bookDTO.getBookName();
        this.authorName=bookDTO.getAuthorName();
        this.bookImage=bookDTO.getBookImage();
        this.quantity=bookDTO.getQuantity();
        this.price=bookDTO.getPrice();
        this.bookDescription=bookDTO.getBookDescription();


    }
}
