package prog21assignment.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class InvalidDtoException extends RuntimeException{
    private List<String> messages = new ArrayList<>();

    public InvalidDtoException(BindingResult br) {
        messages = br.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

    public InvalidDtoException(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
