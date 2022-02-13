package prog21assignment.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Album;
import prog21assignment.exceptions.EntityNotFoundException;
import prog21assignment.repository.QueenEntityRepository;
import prog21assignment.service.QueenEntityService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Album> read() {
        return repository.read();
    }

    @Override
    public Album findById(int id) {
        Optional<Album> album = repository.findById(id);

        if (album.isEmpty()) {
            throw new EntityNotFoundException("Album with id " + id + " doesn't exist");
        }

        return album.get();
    }
}
