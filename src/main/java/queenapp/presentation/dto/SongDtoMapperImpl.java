package queenapp.presentation.dto;

import org.springframework.stereotype.Component;
import queenapp.domain.Song;
import queenapp.exception.InvalidDtoException;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

@Component
public class SongDtoMapperImpl implements QueenEntityDtoMapper<SongDto, Song> {
    @Override
    public Song fromDto(SongDto dto, Song s) {
        s.setTitle(dto.getTitle());
        s.setDescription(dto.getDescription());
        s.setLength(dto.getLength());
        s.setGenreOrdinals(dto.getGenreOrdinals());
        s.setFinishedRecording(parseFinishedRecording(dto.getFinishedRecording()));
        return s;
    }

    @Override
    public SongDto toDto(SongDto dto, Song s) {
        dto.setId(s.getId());
        dto.setTitle(s.getTitle());
        dto.setGenres(s.genresToString());
        dto.setDescription(s.getDescription());
        dto.setLength(s.getLength());
        dto.setGenreOrdinals(s.getGenreOrdinals());
        dto.setFinishedRecording(s.getFinishedRecording().toString());
        return dto;
    }

    @Override
    public SongDto toDto(Song s) {
        return toDto(new SongDto(), s);
    }

    private YearMonth parseFinishedRecording(String finishedRecording) {
        try {
            return YearMonth.parse(finishedRecording);
        } catch (DateTimeParseException e) {
            throw new InvalidDtoException(e.getMessage());
        }
    }
}
