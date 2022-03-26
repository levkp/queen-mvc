package queenapp.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import queenapp.domain.Song;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SongDto extends QueenEntityDto {
    private final Logger log = LoggerFactory.getLogger(getClass());

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

    public String genresToString;

    public SongDto() {
        log.debug("Default constructor invoked");
    }

    // Unfortunately, Lombok can't generate this constructor with @AllArgsConstructor, because it contains superclass
    // attributes too
    public SongDto(int id, String title, String description, String recorded, double length, int albumId, List<Integer> genreOrdinals, String genres) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.recorded = recorded;
        this.length = length;
        this.albumId = albumId;
        this.genreOrdinals = genreOrdinals;
        this.genresToString = genres;
    }

    public static SongDto fromSong(Song s) {
        return new SongDto(s.getId(), s.getTitle(), s.getDescription(), s.getFinishedRecording().toString(),
                s.getLength(), s.getAlbum().getId(), s.getGenreOrdinals(), s.genresToString());
    }
}
