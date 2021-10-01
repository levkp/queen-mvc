package prog21assignment.repository;

import prog21assignment.domain.Song;
import java.util.List;

public interface SongRepository {
    Song createSong(Song s);
    List<Song> readSongs();
}
