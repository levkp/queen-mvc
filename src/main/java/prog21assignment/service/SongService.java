package prog21assignment.service;

import prog21assignment.domain.Album;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;

import java.time.YearMonth;
import java.util.List;

@SuppressWarnings("unused")
public interface SongService {
    Song addSong(String title, double length, List<Genre> genres, YearMonth finishedRecording, Album album);
    List<Song> getAllSongs();
}
