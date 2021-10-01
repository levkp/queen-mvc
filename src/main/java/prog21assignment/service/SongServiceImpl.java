package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;
import prog21assignment.repository.SongRepository;

import java.time.YearMonth;
import java.util.List;

@Component
public class SongServiceImpl implements SongService {

    private final SongRepository repository;

    @Autowired
    public SongServiceImpl(SongRepository repository) {
        this.repository = repository;
    }

    @Override
    public Song addSong(String title, double length, List<Genre> genres, YearMonth finishedRecording, Album album) {
        Song s = new Song(title, length, genres, finishedRecording, album);
        return repository.createSong(s);
    }

    @Override
    public List<Song> getAllSongs() {
        return repository.readSongs();
    }
}
