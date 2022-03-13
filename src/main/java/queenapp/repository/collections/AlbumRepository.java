package queenapp.repository.collections;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import queenapp.domain.Album;
import queenapp.repository.QueenEntityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("dev2")
@Repository
public class AlbumRepository implements QueenEntityRepository<Album> {
    static List<Album> albums = new ArrayList<>();

    /*
    static {
        log.debug("Seeding album repository");
        seed();
    }

    private static void seed() {
        AlbumRepository repository = new AlbumRepository();
        repository.create(new Album("Queen",  LocalDate.of(1973, 7, 13)));
        repository.create(new Album("Queen II",  LocalDate.of(1974, 3, 8)));
        repository.create(new Album("Sheer Heart Attack",  LocalDate.of(1974, 11, 8)));
        repository.create(new Album("Jazz", LocalDate.of(1978, 11, 10)));
    }
*/

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

    @Override
    public void delete(Album a) {
        albums.remove(a);
    }

    @Override
    public Optional<Album> findById(int id) {
        return albums.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }
}
