package com.example.bookstoreapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


 //Data transfer object of Book Model

@Data
public class BookDTO {

    @NotNull(message = "book name cant be null")
    private String bookName;
    @NotNull(message = "author name cant be null")
    private String authorName;
    @NotNull(message = "book description cant be null")
    private String bookDescription;
    @NotNull(message = "book image cant be null")
    private String bookImage;
    @NotNull (message = "totalPrice cant be empty")
    private Integer price;
    @NotNull(message = "Quantity cant be empty")
    private Integer quantity;


}