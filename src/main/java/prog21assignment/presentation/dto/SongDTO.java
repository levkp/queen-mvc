package prog21assignment.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SongDTO extends QueenEntityDto {
    @Getter @Setter
    @Size(max = 5000, message = "Description can't be longer 5000 characters")
    private String description;

    @Getter @Setter
    @NotNull(message = "Release date is mandatory")
    @NotBlank(message = "Release date is mandatory")
    public String recorded;

//    @Size(max = 9, message = "Song length should be between 0 and 9 minutes")
    @Getter @Setter
    public double length;

    @Getter @Setter
    public int albumId;

    @Getter @Setter
    @NotNull(message = "Song should have at least 1 genre")
    @Size(min = 1, message = "Song should have at least 1 genre")
    public List<Integer> genreOrdinals;
}
