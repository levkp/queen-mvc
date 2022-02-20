package prog21assignment.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@SuppressWarnings("unused")
public class SongDTO extends QueenEntityDto {

    @Size(max = 5000, message = "Description can't be longer 5000 characters")
    private String description;

    @NotNull(message = "Release date is mandatory")
    @NotBlank(message = "Release date is mandatory")
    public String recorded;

//    @Size(max = 9, message = "Song length should be between 0 and 9 minutes")
    public double length;

    public int albumId;

    @NotNull(message = "Song should have at least 1 genre")
    @Size(min = 1, message = "Song should have at least 1 genre")
    public List<Integer> genreOrdinals;

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
