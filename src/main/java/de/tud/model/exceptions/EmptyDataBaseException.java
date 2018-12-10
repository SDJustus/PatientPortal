package de.tud.model.exceptions;

public class EmptyDataBaseException extends RuntimeException {

    public EmptyDataBaseException(String message){
        super(message);
    }
}
