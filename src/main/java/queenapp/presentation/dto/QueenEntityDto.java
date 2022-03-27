package queenapp.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class QueenEntityDto {
    @Getter @Setter
    protected Integer id;

    @Getter @Setter
    @NotNull(message = "Title is mandatory")
    @Size(min = 4, max = 30, message = "Title should have a length between 4 and 30")
    protected String title;

    @Getter @Setter
    @Size(max = 5000, message = "Description can't be longer than 5000 characters")
    protected String description;

    @Getter @Setter
    protected String ownerName;
}
