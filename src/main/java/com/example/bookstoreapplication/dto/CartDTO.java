package com.example.bookstoreapplication.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
// Data transfer object of Cart Model
@Data
public class CartDTO {
    private Integer userId;
    private Integer bookId;
    @NotNull(message="Please provide book quantity")
    private Integer quantity;
    private Integer totalPrice;
}