package queenapp.presentation.mvc.viewmodels;

import lombok.Getter;
import lombok.Setter;

public class RegisterViewModel {
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String passwordConfirmation;
}
