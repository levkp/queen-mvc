package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;
import prog21assignment.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class AlbumServiceImpl implements AlbumService {
    private final QueenEntityRepository<Album> repository;

    @Autowired
    public AlbumServiceImpl(QueenEntityRepository<Album> repository) {
        this.repository = repository;
    }

    @Override
    public Album addAlbum(String title, LocalDate release) {
        return repository.create(new Album(title, release));
    }

    @Override
    public List<Album> getAllAlbums() {
        return repository.read();
    }

    @Override
    public Album findById(int id) {
        return repository.findById(id);
    }
}
