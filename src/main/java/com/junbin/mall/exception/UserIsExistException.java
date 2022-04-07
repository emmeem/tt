package com.junbin.mall.exception;

public class UserIsExistException extends RuntimeException  {
    public UserIsExistException(String message) {
        super(message);
    }

}
