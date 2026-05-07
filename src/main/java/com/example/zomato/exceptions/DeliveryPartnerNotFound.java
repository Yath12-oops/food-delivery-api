package com.example.zomato.exceptions;

public class DeliveryPartnerNotFound extends RuntimeException {
    public DeliveryPartnerNotFound(String message) {
        super(message);
    }
}
