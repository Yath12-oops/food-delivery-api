package com.example.zomato.exceptions;

public class FoodItemNotFound extends RuntimeException {
    public FoodItemNotFound(String message) {
        super(message);
    }
}
