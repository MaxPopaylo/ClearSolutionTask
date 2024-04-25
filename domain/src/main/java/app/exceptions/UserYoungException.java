package app.exceptions;

public class UserYoungException extends RuntimeException {
    public UserYoungException(int maxAge) {
        super("User should be older than " + maxAge);
    }
}
