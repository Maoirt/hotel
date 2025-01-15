package com.example.hotel.exception;

public class AmountIsSmallException extends RuntimeException{
    public AmountIsSmallException(String message) {
        super(message);
    }
}
