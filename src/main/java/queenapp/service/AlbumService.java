package queenapp.service;

import queenapp.domain.Album;
import queenapp.domain.Song;

import java.util.List;

public interface AlbumService extends QueenEntityService<Album> {
    Album create(Album a, List<Song> tracklist, String ownerUsername);
}
