package prog21assignment.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SongDto extends QueenEntityDto {
    @Getter @Setter
    @Size(max = 5000, message = "Description can't be longer than 5000 characters")
    private String description;

    @Getter @Setter
    @NotNull(message = "Release date is mandatory") @NotBlank(message = "Release date is mandatory")
    public String recorded;

    // Todo: constraint?
    @Getter @Setter
    public double length;

    @Getter @Setter
    public int albumId;

    @Getter @Setter
    @NotNull(message = "Song should have at least 1 genre")
    @Size(min = 1, message = "Song should have at least 1 genre")
    public List<Integer> genreOrdinals;
}