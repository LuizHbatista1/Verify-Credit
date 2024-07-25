package com.api.verify_credit.infra.exceptions;


public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id){

        super("Id not found" + id);

    }
}
