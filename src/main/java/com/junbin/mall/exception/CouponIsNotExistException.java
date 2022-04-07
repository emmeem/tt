package com.junbin.mall.exception;

public class CouponIsNotExistException extends RuntimeException  {
    public CouponIsNotExistException(String message) {
        super(message);
    }
}
