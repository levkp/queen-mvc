package prog21assignment.service.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Album;
import prog21assignment.exception.DatabaseException;
import prog21assignment.repository.QueenEntityRepository;
import prog21assignment.service.QueenEntityService;

import java.util.List;

@Profile("jdbc")
@Service
public class AlbumServiceImpl implements QueenEntityService<Album> {
    private final QueenEntityRepository<Album> repository;

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
    public Album findById(int id) {
        Album a = repository.findById(id);

        if (a == null) {
            throw new DatabaseException(id, "Entity not found");
        }

        return a;
    }
}
