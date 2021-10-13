package prog21assignment.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Concert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HardcodedConcertRepository implements ConcertRepository {
    private static final List<Concert> concerts = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(HardcodedConcertRepository.class);

    static {
        log.debug("Seeding concert repository...");
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

        HardcodedAlbumRepository.albums.stream()
                .filter(a -> a.getTitle().equals("Sheer Heart Attack"))
                .findAny()
                .ifPresent(a -> a.getSongs().forEach(s -> {
                    shaManchester.addPlayedSong(s);
                    shaHanley.addPlayedSong(s);
                    shaLiverpool.addPlayedSong(s);
                    shaLeeds.addPlayedSong(s);
                    s.addConcert(shaManchester, shaHanley, shaLiverpool, shaLeeds);
                }));

        HardcodedAlbumRepository.albums.stream()
                .filter(a -> a.getTitle().equals("Queen"))
                .findAny()
                .ifPresent(a -> a.getSongs().forEach(s -> {
                    shaManchester.addPlayedSong(s);
                    s.addConcert(shaManchester);
                }));

        HardcodedAlbumRepository.albums.stream()
                .filter(a -> a.getTitle().equals("Queen II"))
                .findAny()
                .ifPresent(a -> a.getSongs().forEach(s -> {
                    shaHanley.addPlayedSong(s);
                    s.addConcert(shaHanley);
                }));

        HardcodedAlbumRepository.albums.stream()
                .filter(a -> a.getTitle().equals("Jazz"))
                .findAny()
                .ifPresent(a -> a.getSongs().forEach(s -> {
                    shaLiverpool.addPlayedSong(s);
                    shaLeeds.addPlayedSong(s);
                    s.addConcert(shaLiverpool, shaLeeds);
                }));
    }

    @Override
    public Concert createConcert(Concert c) {
        concerts.add(c);
        return c;
    }

    @Override
    public List<Concert> readConcerts() {
        return concerts;
    }
}
