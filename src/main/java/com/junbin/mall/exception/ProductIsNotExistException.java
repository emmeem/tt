package com.junbin.mall.exception;

public class ProductIsNotExistException extends RuntimeException  {
    public ProductIsNotExistException(String message) {
            super(message);
    }
}
