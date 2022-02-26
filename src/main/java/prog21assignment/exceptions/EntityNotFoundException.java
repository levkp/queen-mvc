package prog21assignment.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class c, int id) {
        super("Unable to find " + c.getSimpleName() + " with id " + id);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
