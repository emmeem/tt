package com.junbin.mall.exception;

public class CartInfoIsNotExistException extends RuntimeException  {
    public CartInfoIsNotExistException(String message) {
        super(message);
    }
}