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
    public Album fromDto(AlbumDto dto, Album a) {
        a.setTitle(dto.getTitle());
        a.setRelease(parseRelease(dto.getRelease()));
        a.setDescription(dto.description);
        return a;
    }

    @Override
    public AlbumDto toDto(AlbumDto dto, Album a) {
        dto.setId(a.getId());
        dto.setTitle(a.getTitle());
        dto.setDescription(a.getDescription());
        dto.setRelease(a.getRelease().toString());
        dto.setSongIds(a.getSongs().stream().map(QueenEntity::getId).toList());
        dto.setOwnerName(a.getOwner().getUsername());
        return dto;
    }

    @Override
    public AlbumDto toDto(Album a) {
        return toDto(new AlbumDto(), a);
    }

    private LocalDate parseRelease(String release) {
        try {
            return LocalDate.parse(release);
        } catch (DateTimeParseException e) {
            throw new InvalidDtoException(e.getMessage());
        }
    }
}
