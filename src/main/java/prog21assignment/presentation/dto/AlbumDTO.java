package prog21assignment.presentation.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

// Todo: why do non-static attributes have to be public to make this work
@SuppressWarnings("unused")
public class AlbumDTO {
    public String title;
    public String release;
    private static final Logger log = LoggerFactory.getLogger(AlbumDTO.class);

    public AlbumDTO() {
        log.debug("AlbumDTO default constructor invoked");
    }

    public AlbumDTO(String title, String release) {
        log.debug("AlbumDTO parameterized constructor invoked");
        this.title = title;
        this.release = release;
        log.debug(String.format("%s set as title for AlbumDTO", title));
    }

    public String getTitle() {
        return title;
    }

    public String getRelease() {
        return release;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public LocalDate getParsedRelease() {
        log.debug(String.format("Parsing %s to LocalDate...", release));
        return LocalDate.parse(release);
    }
}
