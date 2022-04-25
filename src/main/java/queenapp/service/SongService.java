package queenapp.service;

import queenapp.domain.Song;

public interface SongService extends QueenEntityService<Song> {
    Song create(Song s, int albumId, String ownerUsername);
}
