package prog21assignment.presentation.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog21assignment.domain.Album;
import prog21assignment.domain.Genre;

import java.time.YearMonth;
import java.util.List;

@SuppressWarnings("unused")
public class SongDTO {
    public String title;
    public double length;

    public String recorded;
    private static final Logger log = LoggerFactory.getLogger(SongDTO.class);

    public Album album;
    public List<Genre> genres;

    public SongDTO(String title, double length, String recorded) {
        log.debug("SongDTO parameterized constructor invoked");
        this.title = title;
        log.debug(String.format("%s set as title for SongDTO", title));
        this.length = length;
        log.debug(String.format("%f set as length for SongDTO", length));
        this.recorded = recorded;
        log.debug(String.format("%s set as recorded date for SongDTO", recorded));

        this.album = null;
        genres = List.of(Genre.GLAM_ROCK);
    }

    public String getTitle() {
        return title;
    }

    public double getLength() {
        return length;
    }

    public String getRecorded() {
        return recorded;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setRecorded(String recorded) {
        this.recorded = recorded;
    }

    public YearMonth getParsedRecordedDate() {
        log.debug(String.format("Parsing %s to YearMonth...", recorded));
        return YearMonth.parse(recorded);
    }
}
