package queenapp.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SongDto extends QueenEntityDto {
    @Getter @Setter
    @NotNull(message = "Release date is mandatory") @NotBlank(message = "Release date is mandatory")
    private String finishedRecording;

    // Todo: constraint?
    @Getter @Setter
    private double length;

    @Getter @Setter
    private Integer albumId;

    @Getter @Setter
    @NotNull(message = "Song should have at least 1 genre")
    @Size(min = 1, message = "Song should have at least 1 genre")
    private List<Integer> genreOrdinals;

    @Getter @Setter
    private String genres;

    public SongDto() {
        LoggerFactory.getLogger(getClass()).debug("Default constructor invoked");
    }
}
