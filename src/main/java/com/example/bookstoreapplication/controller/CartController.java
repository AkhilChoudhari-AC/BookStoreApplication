package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.CartDTO;
import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.model.Cart;
import com.example.bookstoreapplication.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

// In Controller class we write the API's

@RestController
@RequestMapping("/cart")

public class CartController {

  // Autowired ICartService to inject its dependency here

    @Autowired
    ICartService cartService;

    //Ability to call api to insert all cart records
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        Cart newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    // Ability to call api to retrieve all cart records
    @GetMapping("/getAll")
    public ResponseDTO getAllCartDetails() {
        ResponseDTO responseDTO = cartService.getCartDetails();
        return responseDTO;
    }

    // Ability to call api to retrieve cart record by cartId
    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable Integer cartId){
        Optional<Cart> specificCartDetail=cartService.getCartDetailsById(cartId);
        ResponseDTO responseDTO=new ResponseDTO("Cart details retrieved successfully",specificCartDetail);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

    // Ability to call api to retrieve cart record by book id
    @GetMapping("/retrieveCartByBookId/{bookID}")
    public ResponseEntity<ResponseDTO> getCartRecordByBookId(@PathVariable Integer bookID){
        Cart newCart = cartService.getCartRecordByBookId(bookID);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !",newCart);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

   // Ability to call api to delete cart by id
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId) {
        Optional<Cart> delete = cartService.deleteCartItemById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", delete);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    // Ability to call api to update cart by id
    // It Increase Cart Quantity
    // it Decrease Cart Quantity

    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable Integer cartId,@Valid @RequestBody CartDTO cartDTO){
        Cart updateRecord = cartService.updateRecordById(cartId,cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }


}
