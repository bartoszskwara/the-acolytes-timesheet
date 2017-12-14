package pl.lso.kazimierz.theacolytestimesheet.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
