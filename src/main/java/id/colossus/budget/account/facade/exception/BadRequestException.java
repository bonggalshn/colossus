package id.colossus.budget.account.facade.exception;

/**
 * Exception thrown for bad request errors (validation failures).
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}