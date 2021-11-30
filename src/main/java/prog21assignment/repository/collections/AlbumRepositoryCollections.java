package prog21assignment.repository.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Album;
import prog21assignment.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Repository
public class AlbumRepositoryCollections implements QueenEntityRepository<Album> {
    static List<Album> albums = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(AlbumRepositoryCollections.class);

    static {
        log.debug("Seeding album repository");
        seed();
    }

    private static void seed() {
        AlbumRepositoryCollections repository = new AlbumRepositoryCollections();
        repository.create(new Album("Queen",  LocalDate.of(1973, 7, 13)));
        repository.create(new Album("Queen II",  LocalDate.of(1974, 3, 8)));
        repository.create(new Album("Sheer Heart Attack",  LocalDate.of(1974, 11, 8)));
        repository.create(new Album("Jazz", LocalDate.of(1978, 11, 10)));
    }

    @Override
    public Album create(Album a) {
        a.setId(albums.size());
        albums.add(a);
        return a;
    }

    @Override
    public List<Album> read() {
        return albums;
    }

    // Todo
    @Override
    public void update(Album album) {

    }

    @Override
    public void delete(Album album) {
        albums.remove(album);
    }

    @Override
    public Album findById(int id) {
        Optional<Album> o = albums.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        return o.isEmpty() ? null : o.get();
    }
}
