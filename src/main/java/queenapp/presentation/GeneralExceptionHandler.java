package queenapp.presentation;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import queenapp.exception.EntityNotFoundException;
import queenapp.exception.InvalidDtoException;
import queenapp.exception.OwnershipException;

import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        // Todo: handle this better, in a more user-friendly way
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getCause().getCause().getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InvalidDtoException.class)
    public ResponseEntity<List<String>> handleInvalidDto(InvalidDtoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessages());
    }

    @ExceptionHandler(OwnershipException.class)
    public ResponseEntity<String> handleOwnerShipException(OwnershipException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
