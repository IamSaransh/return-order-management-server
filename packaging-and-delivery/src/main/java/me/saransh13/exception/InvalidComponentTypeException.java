package me.saransh13.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidComponentTypeException extends Exception {
    public InvalidComponentTypeException(String message) {
        super(message);
    }
}