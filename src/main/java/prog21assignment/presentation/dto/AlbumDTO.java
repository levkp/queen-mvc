package prog21assignment.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog21assignment.domain.Album;
import prog21assignment.domain.QueenEntity;
import prog21assignment.exceptions.InvalidDtoException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlbumDTO extends QueenEntityDto {
    @Getter
    @Size(max = 5000, message = "Description can't be longer 5000 characters")
    private String description;

    // Todo
    @Getter @Setter
    @NotNull(message = "Release date must not be null")
    @NotBlank(message = "Release date must not be blank")
    private String release;

    @Getter @Setter
    @NotNull(message = "Song ids must not be null")
    private List<Integer> songIds = new ArrayList<>();

    private final Logger log = LoggerFactory.getLogger(getClass());

    public AlbumDTO() {
        log.debug("Default constructor invoked");
    }

    public AlbumDTO(String title, String description, String release, List<Integer> songIds) {
        this.title = title;
        this.description = description;
        this.release = release;
        this.songIds = songIds;
    }

    public AlbumDTO(int id, String title, String description, String release, List<Integer> songIds) {
        this(title, description, release, songIds);
        this.id = id;
    }


    // Todo: why does parsed release get included in response bodies?!
    public LocalDate getParsedRelease() {
        log.debug(String.format("Parsing %s to LocalDate", release));

        try {
            return LocalDate.parse(release);
        } catch (DateTimeException e) {
            throw new InvalidDtoException(e.getMessage());
        }
    }

    public static AlbumDTO fromAlbum(Album a) {
        List<Integer> songIds = a.getSongs().stream().map(QueenEntity::getId).toList();
        return new AlbumDTO(a.getId(), a.getTitle(), a.getDescription(), a.getRelease().toString(), songIds);
    }
}
