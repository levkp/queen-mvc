package prog21assignment.service.jpa_rep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityJpaRepository;
import prog21assignment.service.QueenEntityService;

import java.util.List;

@Profile("jpa_rep")
@Service
public class SongServiceImpl implements QueenEntityService<Song> {
    private final QueenEntityJpaRepository<Song> repository;

    @Autowired
    public SongServiceImpl(QueenEntityJpaRepository<Song> repository) {
        this.repository = repository;
    }

    @Override
    public Song create(Song s) {
        return repository.save(s);
    }

    @Override
    public List<Song> read() {
        return repository.findAll();
    }

    @Override
    public Song findById(int id) {
        return repository.findById(id).orElse(null);
    }
}
