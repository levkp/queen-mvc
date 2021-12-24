package prog21assignment.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Song;
import prog21assignment.exceptions.DatabaseException;
import prog21assignment.repository.QueenEntityRepository;
import prog21assignment.service.QueenEntityService;

import java.util.List;

@Profile("jpa")
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
    public Song findById(int id) {
        Song s = repository.findById(id);

        if (s == null) {
            throw new DatabaseException(id, "Entity not found");
        }

        return s;
    }
}
