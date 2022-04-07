package com.junbin.mall.exception;

public class UserIsNotExistException extends RuntimeException {
    public UserIsNotExistException(String message) {
        super(message);
    }
}
