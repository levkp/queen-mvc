package prog21assignment.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityJpaRepository;
import prog21assignment.service.QueenEntityService;

import java.util.List;

@Profile("prod")
@Service
public class SongServiceImpl implements QueenEntityService<Song> {
    
    QueenEntityJpaRepository<Song> repository;

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
        return null;
    }

    @Override
    public void update(Song s) {

    }

    @Override
    public void delete(Song s) {
        repository.delete(s);
    }

    @Override
    public Song findById(int id) {
        return null;
    }
}
