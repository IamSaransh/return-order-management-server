package me.saransh13.authorizationserver.exception;

/**
 * @author saransh
 */
public class UsernameExistException extends  Exception{

    public UsernameExistException(String message) {
        super(message);
    }
}
