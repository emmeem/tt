package com.junbin.mall.exception;

public class OrderIsNotExist extends RuntimeException  {
    public OrderIsNotExist(String message) {
        super(message);
    }
}
