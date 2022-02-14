package prog21assignment.exceptions;

public class NoContentException extends RuntimeException{

    public NoContentException(String message) {
        super(message);
    }

    public NoContentException() {
    }
}
