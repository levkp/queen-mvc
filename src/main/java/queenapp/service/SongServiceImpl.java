package queenapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import queenapp.domain.Song;
import queenapp.exception.EntityNotFoundException;
import queenapp.repository.QueenEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
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
    public Song update(Song s) {
        return repository.update(s);
    }

    @Override
    public Song findById(int id) {
        Optional<Song> o = repository.findById(id);
        if (o.isEmpty()) throw new EntityNotFoundException("Unable to find song with id " + id);
        return o.get();
    }
}
