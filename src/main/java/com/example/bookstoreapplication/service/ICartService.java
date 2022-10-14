package com.example.bookstoreapplication.service;

import com.example.bookstoreapplication.dto.CartDTO;
import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.model.Cart;

import java.util.Optional;

// Created ICartService interface to achieve abstraction

public interface ICartService {

    ResponseDTO getCartDetails();

    Optional<Cart> getCartDetailsById(Integer cartId);

    Optional<Cart> deleteCartItemById(Integer cartId);

    Cart updateRecordById(Integer cartId, CartDTO cartDTO);

    Cart insertItems(CartDTO cartdto);

    Cart getCartRecordByBookId(Integer bookID);

}