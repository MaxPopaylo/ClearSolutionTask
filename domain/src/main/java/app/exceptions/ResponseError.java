package app.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseError {

    private final String message;
    private final LocalDateTime dateTime = LocalDateTime.now();

    public ResponseError(String message) {
        this.message = message;
    }
}