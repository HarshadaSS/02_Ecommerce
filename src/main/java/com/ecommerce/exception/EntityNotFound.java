package com.ecommerce.exception;

public class EntityNotFound extends RuntimeException{

    public EntityNotFound(String message){
        super(message);
    }
}
