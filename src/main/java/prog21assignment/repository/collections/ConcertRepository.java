package prog21assignment.repository.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Concert;
import prog21assignment.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("dev2")
@Repository
public class ConcertRepository implements QueenEntityRepository<Concert> {
    private static final List<Concert> concerts = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(ConcertRepository.class);

    static {
        log.debug("Seeding concert repository");
        seed();
    }

    private static void seed() {
        Concert shaManchester = new Concert(1904, "Sheer Heart Attack Manchester", "Palace Theatre, Manchester",
                LocalDate.of(1974, 10, 31));
        Concert shaHanley = new Concert(1364, "Sheer Heart Attack Shanley", "Victoria Hall, Shanley",
                LocalDate.of(1974, 10, 31));
        Concert shaLiverpool = new Concert(1841, "Sheer Heart Attack Liverpool", "Empire Theatre, Liverpool",
                LocalDate.of(1974, 11, 1));
        Concert shaLeeds = new Concert(1024, "Sheer Heart Attack Leeds", "University of Leeds Refectory",
                LocalDate.of(1974, 11, 2));

        concerts.addAll(List.of(shaManchester, shaHanley, shaLiverpool, shaLeeds));

        AlbumRepository.albums.stream()
                .filter(a -> a.getTitle().equals("Sheer Heart Attack"))
                .findAny()
                .ifPresent(a -> a.getSongs().forEach(s -> {
                    shaManchester.addSongs(s);
                    shaHanley.addSongs(s);
                    shaLiverpool.addSongs(s);
                    shaLeeds.addSongs(s);
                    s.addConcert(shaManchester, shaHanley, shaLiverpool, shaLeeds);
                }));

        AlbumRepository.albums.stream()
                .filter(a -> a.getTitle().equals("Queen"))
                .findAny()
                .ifPresent(a -> a.getSongs().forEach(s -> {
                    shaManchester.addSongs(s);
                    s.addConcert(shaManchester);
                }));

        AlbumRepository.albums.stream()
                .filter(a -> a.getTitle().equals("Queen II"))
                .findAny()
                .ifPresent(a -> a.getSongs().forEach(s -> {
                    shaHanley.addSongs(s);
                    s.addConcert(shaHanley);
                }));

        AlbumRepository.albums.stream()
                .filter(a -> a.getTitle().equals("Jazz"))
                .findAny()
                .ifPresent(a -> a.getSongs().forEach(s -> {
                    shaLiverpool.addSongs(s);
                    shaLeeds.addSongs(s);
                    s.addConcert(shaLiverpool, shaLeeds);
                }));
    }

    @Override
    public Concert create(Concert c) {
        c.setId(concerts.size());
        concerts.add(c);
        return c;
    }

    @Override
    public List<Concert> read() {
        return concerts;
    }

    @Override
    public void update(Concert concert) {

    }

    @Override
    public void delete(Concert concert) {

    }

    @Override
    public Concert findById(int id) {
        Optional<Concert> o = concerts.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        return o.isEmpty() ? null : o.get();
    }
}
