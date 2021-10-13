package prog21assignment.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HardcodedAlbumRepository implements AlbumRepository {
    static List<Album> albums = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(HardcodedAlbumRepository.class);

    static {
        log.debug("Seeding album repository...");
        seed();
    }

    private static void seed() {
        Album queen = new Album("Queen",  LocalDate.of(1973, 7, 13));
        Album queenII = new Album("Queen II",  LocalDate.of(1974, 3, 8));
        Album sheerHeartAttack = new Album("Sheer Heart Attack",  LocalDate.of(1974, 11, 8));
        Album jazz = new Album("Jazz", LocalDate.of(1978, 11, 10));

        albums.addAll(List.of(queen, queenII, sheerHeartAttack, jazz));
    }

    @Override
    public Album createAlbum(Album a) {
        albums.add(a);
        return a;
    }

    @Override
    public List<Album> readAlbums() {
        return albums;
    }
}
