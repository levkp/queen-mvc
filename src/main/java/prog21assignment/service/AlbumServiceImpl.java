package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;
import prog21assignment.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.util.List;

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
    public void update(Album album) {

    }

    @Override
    public void delete(Album album) {

    }

    @Override
    public Album findById(int id) {
        return repository.findById(id);
    }
}
