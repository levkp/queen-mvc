package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;
import prog21assignment.repository.AlbumRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository repository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository repository) {
        this.repository = repository;
    }

    @Override
    public Album addAlbum(String title, LocalDate release) {
        Album a = new Album(title, release);
        return repository.createAlbum(a);
    }

    @Override
    public List<Album> getAllAlbums() {
        return repository.readAlbums();
    }
}
