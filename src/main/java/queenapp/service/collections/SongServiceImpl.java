package queenapp.service.collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import queenapp.domain.Song;
import queenapp.exception.EntityNotFoundException;
import queenapp.repository.QueenEntityRepository;
import queenapp.service.QueenEntityService;

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
