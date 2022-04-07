package com.junbin.mall.handler;

import com.junbin.mall.error.ErrorResult;
import com.junbin.mall.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> validHandler(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler({UserIsNotExistException.class, ProductIsNotExistException.class,
            CouponIsNotExistException.class, OrderIsNotExist.class,
    CartInfoIsNotExistException.class, MissionIsNotExistException.class})
    public ResponseEntity<ErrorResult> existHandler(Exception ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler({UserIsExistException.class,UserPasswordIsNotCorrectException.class,
            CompanyIsExistException.class, CouponIsExistException.class,
            MissionIsExistException.class})
    public ResponseEntity<ErrorResult> correctHandler(Exception ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

}
