package queenapp.service;

import queenapp.domain.Song;
import queenapp.presentation.dto.SongDto;

public interface SongService extends QueenEntityService<Song> {
    SongDto createFromDto(SongDto dto, String ownerUsername);
    SongDto updateFromDto(SongDto dto, String ownerUsername);


}
