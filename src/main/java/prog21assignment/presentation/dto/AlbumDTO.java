package prog21assignment.presentation.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public class AlbumDTO {
    @NotNull
    @Size(min=2, max=40)
    private String title;

    @NotNull
    private String release;

    @NotNull
    private List<Integer> songIds;

    private static final Logger log = LoggerFactory.getLogger(AlbumDTO.class);

    public AlbumDTO() {
        log.debug("AlbumDTO default constructor invoked");
    }

    public String getTitle() {
        return title;
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
        log.debug(String.format("Parsing %s to LocalDate...", release));
        return LocalDate.parse(release);
    }
}
