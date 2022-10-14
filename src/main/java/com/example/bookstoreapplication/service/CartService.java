package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.CartDTO;
import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.exception.UserException;
import com.example.bookstoreapplication.exception.UserExceptionalHandler;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.model.Cart;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.repository.BookStoreCartRepository;
import com.example.bookstoreapplication.repository.BookStoreRepository;
import com.example.bookstoreapplication.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j

// Created CartService class to serve api calls done by controller layer

public class CartService implements ICartService {

    // Autowired interfaces to inject its dependency here

    @Autowired
    BookStoreRepository bookStoreRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookStoreCartRepository bookStoreCartRepository;

    // create a method name as insertItems
    //Ability to save cart details to repository

    public Cart insertItems(CartDTO cartdto) {
        Optional<Book> book = bookStoreRepository.findById(cartdto.getBookId());
        Optional<User> user = userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (cartdto.getQuantity() <= book.get().getQuantity()) {
                int quantity = book.get().getQuantity() - cartdto.getQuantity();
                Cart newCart = new Cart(cartdto.getQuantity(), book.get(), user.get(), book.get().getPrice() * cartdto.getQuantity());
                bookStoreCartRepository.save(newCart);
                return newCart;
            } else {
                throw new UserException("Requested quantity is not available");
            }
        } else {
            throw new UserException("Book or User doesn't exists");
        }

    }

    //create a method name as getCartDetails
    //Ability to get all cart' data by findAll() method

    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails = bookStoreCartRepository.findAll();
        ResponseDTO dto = new ResponseDTO();
        if (getCartDetails.isEmpty()) {
            String message = " Cart details not found";
            dto.setMessage(message);
            dto.setData(0);
            return dto;

        } else {
            dto.setMessage("the list of cart items is successfully retrieved");
            dto.setData(getCartDetails);
            return dto;
        }
    }
    // create a method name as getCartDetailsById
    // Ability to get cart data by cartId

    @Override
    public Optional<Cart> getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData = bookStoreCartRepository.findById(cartId);
        if (getCartData.isPresent()) {
            return getCartData;
        } else {
            throw new UserException(" Didn't find any record for this particular cartId");
        }
    }

    // create a method name as getCartRecordByBookId
    // Ability to get cart data by bookId

    public Cart getCartRecordByBookId(Integer bookId) {
        Optional<Cart> cart = bookStoreCartRepository.findByBookId(bookId);
        if (cart.isEmpty()) {
            return null;
            //throw new BookStoreException("Cart Record doesn't exists");
        } else {
            log.info("Cart record retrieved successfully for book id " + bookId);
            return cart.get();
        }
    }



    // create a method name as deleteCartItemById
    // ability to delete data by particular cart id

    @Override
    public Optional<Cart> deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData = bookStoreCartRepository.findById(cartId);
        if (deleteData.isPresent()) {
            bookStoreCartRepository.deleteById(cartId);
            return deleteData;
        } else {
            throw new UserException(" Did not get any cart for cart id ");
        }

    }

    //create a method name as updateRecordById
    // Ability to update cart data for particular id

    @Override
    public Cart updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = bookStoreCartRepository.findById(cartId);
        Optional<Book> book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<User> user = userRepository.findById(cartDTO.getUserId());
        if (cart.isEmpty()) {
            throw new UserException("Cart Record doesn't exists");
        } else {
            if (book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartId, cartDTO.getQuantity(), book.get(), user.get(), book.get().getPrice() * cartDTO.getQuantity());
                bookStoreCartRepository.save(newCart);
                log.info("Cart record updated successfully for id " + cartId);
                return newCart;
            } else {
                throw new UserException("Book or User doesn't exists");
            }
        }


    }
}
