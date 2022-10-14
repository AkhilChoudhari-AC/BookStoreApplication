package com.example.bookstoreapplication.model;

import lombok.Data;
import javax.persistence.*;

// The @Entity annotation specifies that the class is an entity and is mapped to a database table

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

     //@ManyToOne mapping means that one parent record can have multiple child records.
     // In other words, multiple records of a table can associate themselves with a common record in another table.
    @ManyToOne
    // @JoinColumn is used to specify a column for joining an entity association
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
    private Integer quantity;
    private Integer totalPrice;

    public Cart(Integer cartId, Integer quantity, Book book, User user, Integer totalPrice) {
        super();
        this.cartId = cartId;
        this.quantity = quantity;
        this.book = book;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public Cart(Integer quantity, Book book, User user, Integer totalPrice) {
        super();
        this.quantity = quantity;
        this.book = book;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public Cart() {

    }
}