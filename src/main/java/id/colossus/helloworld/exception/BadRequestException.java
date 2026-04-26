package id.colossus.helloworld.exception;

/**
 * Exception thrown when a request is invalid or malformed.
 * 
 * @author Colossus Team
 * @version 1.0.0
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}