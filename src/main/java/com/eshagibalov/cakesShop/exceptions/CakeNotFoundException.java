package com.eshagibalov.cakesShop.exceptions;

public class CakeNotFoundException extends RuntimeException {

    public CakeNotFoundException(String message) {
        super(message);
    }
}
