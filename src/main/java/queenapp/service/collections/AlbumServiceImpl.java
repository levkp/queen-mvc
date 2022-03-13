package queenapp.service.collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import queenapp.domain.Album;
import queenapp.exception.EntityNotFoundException;
import queenapp.repository.QueenEntityRepository;
import queenapp.service.QueenEntityService;

import java.util.List;

@Profile("dev2")
@Component
public class AlbumServiceImpl implements QueenEntityService<Album> {
    private final QueenEntityRepository<Album> repository;

    @Autowired
    public AlbumServiceImpl(QueenEntityRepository<Album> repository) {
        this.repository = repository;
    }

    @Override
    public Album create(Album a) {
        return repository.create(a);
    }

    @Override
    public List<Album> read() {
        return repository.read();
    }

    @Override
    public void delete(int id) {
        repository.delete(findById(id));
    }

    @Override
    public Album findById(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find album with id " + id));
    }
}
