package prog21assignment.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.List;

public class InvalidDtoException extends RuntimeException{
    private final List<String> messages;

    public InvalidDtoException(BindingResult br) {
        messages = br.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

    public List<String> getMessages() {
        return messages;
    }
}
