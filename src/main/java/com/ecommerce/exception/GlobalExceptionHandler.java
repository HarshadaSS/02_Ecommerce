package com.ecommerce.exception;

import com.ecommerce.bean.GlobalResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFound.class})
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFound entityNotFound){
        return GlobalResponseHandler.createResponse(entityNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }
}
