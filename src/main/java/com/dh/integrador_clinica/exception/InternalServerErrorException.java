package com.dh.integrador_clinica.exception;

import org.springframework.dao.DataAccessException;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String message, DataAccessException e) {
        super(message);
    }
}
