package queenapp.service;

import queenapp.domain.Song;
import queenapp.presentation.dto.SongDto;

public interface SongService extends QueenEntityService<Song> {
    void createFromDto(SongDto dto, String ownerUsername);
    void updateFromDto(SongDto dto, String ownerUsername);


}
