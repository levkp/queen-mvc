package queenapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import queenapp.domain.Album;
import queenapp.exception.EntityNotFoundException;
import queenapp.repository.QueenEntityRepository;

import java.util.List;

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
        return repository.read();
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
