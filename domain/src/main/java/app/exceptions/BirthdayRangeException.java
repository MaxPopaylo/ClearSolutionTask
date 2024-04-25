package app.exceptions;

public class BirthdayRangeException extends RuntimeException {
    public BirthdayRangeException() {
        super("Birthday range wrong");
    }
}
