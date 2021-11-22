package prog21assignment.presentation.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@SuppressWarnings("unused")
public class SongDTO {
    @NotNull(message = "Title is mandatory")
    @Size(min = 4, max = 40, message = "Title should have a length between 4 and 40")
    public String title;

    @Size(max = 5000, message = "Description can't be longer 5000 characters")
    private String description;

    @NotNull(message = "Release date is mandatory")
    public String recorded;

    @Size(min = 0, max = 9, message = "Song length should be between 0 and 9 minutes")
    public double length;

    public int albumId;

    @NotNull(message = "Song should have at least 1 genre")
    @Size(min = 1)
    public List<Integer> genreOrdinals;

    private static final Logger log = LoggerFactory.getLogger(SongDTO.class);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getRecorded() {
        return recorded;
    }

    public void setRecorded(String recorded) {
        this.recorded = recorded;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public List<Integer> getGenreOrdinals() {
        return genreOrdinals;
    }

    public void setGenreOrdinals(List<Integer> genreOrdinals) {
        this.genreOrdinals = genreOrdinals;
    }
}
