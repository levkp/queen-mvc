package queenapp.presentation.dto;

import org.springframework.stereotype.Component;
import queenapp.domain.Album;
import queenapp.domain.QueenEntity;
import queenapp.exception.InvalidDtoException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class AlbumDtoMapperImpl implements QueenEntityDtoMapper<AlbumDto, Album> {
    @Override
    public Album fromDto(AlbumDto dto) {
        return new Album(
                dto.getTitle(),
                parseRelease(dto.getRelease()),
                dto.getDescription());
    }

    @Override
    public AlbumDto toDto(Album a) {
        return new AlbumDto(
                a.getId(),
                a.getTitle(),
                a.getDescription(),
                a.getRelease().toString(),
                a.getSongs().stream().map(QueenEntity::getId).toList(),
                a.getOwner().getUsername());
    }

    private LocalDate parseRelease(String release) {
        try {
            return LocalDate.parse(release);
        } catch (DateTimeParseException e) {
            throw new InvalidDtoException(e.getMessage());
        }
    }
}
