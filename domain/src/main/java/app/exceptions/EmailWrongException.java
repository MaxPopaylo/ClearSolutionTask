package app.exceptions;

public class EmailWrongException extends RuntimeException {
    public EmailWrongException() {
        super("Email wrong");
    }
}
