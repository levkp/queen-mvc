package prog21assignment.service.collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Song;
import prog21assignment.exception.EntityNotFoundException;
import prog21assignment.repository.QueenEntityRepository;
import prog21assignment.service.QueenEntityService;

import java.util.List;
import java.util.Optional;

@Profile("dev2")
@Component
public class SongServiceImpl implements QueenEntityService<Song> {

    private final QueenEntityRepository<Song> repository;

    @Autowired
    public SongServiceImpl(QueenEntityRepository<Song> repository) {
        this.repository = repository;
    }

    @Override
    public Song create(Song s) {
        return repository.create(s);
    }

    @Override
    public List<Song> read() {
        return repository.read();
    }

    @Override
    public Song findById(int id) {
        Optional<Song> song = repository.findById(id);

        if (song.isEmpty()) {
            throw new EntityNotFoundException("Unable to find song with id " + id);
        }

        return song.get();
    }
}
