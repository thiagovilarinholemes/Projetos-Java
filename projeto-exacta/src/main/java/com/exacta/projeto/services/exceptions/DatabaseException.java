package com.exacta.projeto.services.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg){
        super(msg);
    }
}
