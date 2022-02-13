package prog21assignment.presentation.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog21assignment.domain.Album;
import prog21assignment.domain.QueenEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AlbumDTO {
    @NotNull(message = "Title is mandatory")
    @Size(min = 4, max = 30, message = "Title should have a length between 4 and 30")
    public String title;

    @Size(max = 5000, message = "Description can't be longer 5000 characters")
    private String description;

    @NotNull(message = "Release date is mandatory")
    @NotBlank(message = "Release date is mandatory")
    private String release;

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRelease() {
        return release;
    }

    public List<Integer> getSongIds() {
        return songIds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public void setSongIds(List<Integer> songIds) {
        this.songIds = songIds;
    }

    public LocalDate getParsedRelease() {
        log.debug(String.format("Parsing %s to LocalDate", release));
        return LocalDate.parse(release);
    }

    public static AlbumDTO fromAlbum(Album a) {
        List<Integer> songIds = a.getSongs().stream()
            .map(QueenEntity::getId).toList();
        return new AlbumDTO(a.getTitle(), a.getDescription(), a.getRelease().toString(), songIds);
    }
}
