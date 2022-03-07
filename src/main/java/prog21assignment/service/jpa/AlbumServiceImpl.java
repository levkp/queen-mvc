package prog21assignment.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Album;
import prog21assignment.exception.EntityNotFoundException;
import prog21assignment.exception.NoContentException;
import prog21assignment.repository.QueenEntityRepository;
import prog21assignment.service.QueenEntityService;

import java.util.List;

@Profile("jpa")
@Service
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

    // Todo: remove exception
    @Override
    public List<Album> read() {
        List<Album> albums = repository.read();
        if (albums.isEmpty()) throw new NoContentException();
        return albums;
    }

    @Override
    public Album update(Album a) {
        return repository.update(a);
    }

    @Override
    public void delete(int id) {
        repository.delete(findById(id));
    }

    @Override
    public Album findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find album with id " + id));
    }
}
