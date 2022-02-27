//package prog21assignment.exceptions;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entity not found")
//public class DatabaseException extends RuntimeException {
//    private final int id;
//
//    public DatabaseException(int id, String message) {
//        super(message);
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//}
