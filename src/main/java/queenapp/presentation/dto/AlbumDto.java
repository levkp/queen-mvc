package queenapp.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import queenapp.domain.Album;
import queenapp.domain.QueenEntity;
import queenapp.exception.InvalidDtoException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AlbumDto extends QueenEntityDto {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Getter @Setter
    @NotNull(message = "Release date must not be null") @NotBlank(message = "Release date must not be blank")
    private String release;

    @Getter
    private final Set<String> genres = new HashSet<>();

    @Getter @Setter
    private double length;

    @Getter @Setter
    @NotNull(message = "Song ids must not be null")
    private List<Integer> songIds = new ArrayList<>();

    public AlbumDto() {
        log.debug("Default constructor invoked");
    }

    public AlbumDto(String title, String release) {
        this.title = title;
        this.release = release;
    }

    public AlbumDto(String title, String description, String release, List<Integer> songIds) {
        this(title, release);
        this.description = description;
        this.songIds = songIds;
    }

    public AlbumDto(int id, String title, String description, String release, List<Integer> songIds) {
        this(title, description, release, songIds);
        this.id = id;
    }

    public AlbumDto(int id, String title, String description, String release, List<Integer> songIds, String ownerName) {
        this(id, title, description, release, songIds);
        this.ownerName = ownerName;
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

    public static AlbumDto fromAlbum(Album a) {
        return new AlbumDto(
                a.getId(),
                a.getTitle(),
                a.getDescription(),
                a.getRelease().toString(),
                a.getSongs().stream().map(QueenEntity::getId).collect(Collectors.toList()),
                a.getOwner().getUsername());
    }
}
