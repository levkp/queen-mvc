package queenapp.exception;

@SuppressWarnings("rawtypes")
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class c, int id) {
        super("Unable to find " + c.getSimpleName() + " with id " + id);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {

    }
}
