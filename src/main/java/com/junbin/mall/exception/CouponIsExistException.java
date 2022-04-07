package com.junbin.mall.exception;

public class CouponIsExistException extends RuntimeException  {
    public CouponIsExistException(String message) {
        super(message);
    }
}
