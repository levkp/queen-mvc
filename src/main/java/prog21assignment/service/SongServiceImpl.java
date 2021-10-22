package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityRepository;

import java.time.YearMonth;
import java.util.List;

@Component
public class SongServiceImpl implements SongService {

    private final QueenEntityRepository<Song> repository;

    @Autowired
    public SongServiceImpl(QueenEntityRepository<Song> repository) {
        this.repository = repository;
    }

    @Override
    public Song create(String title, double length, List<Genre> genres, YearMonth finishedRecording, Album album) {
        return repository.create(new Song(title, length, genres, finishedRecording, album));
    }

    @Override
    public List<Song> read() {
        return repository.read();
    }

    @Override
    public Song findById(int id) {
        return repository.findById(id);
    }
}
