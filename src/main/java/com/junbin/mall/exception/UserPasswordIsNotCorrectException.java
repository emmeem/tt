package com.junbin.mall.exception;

public class UserPasswordIsNotCorrectException extends RuntimeException {
    public UserPasswordIsNotCorrectException(String message) {
        super(message);
    }
}
