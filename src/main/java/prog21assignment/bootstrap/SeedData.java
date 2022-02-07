package prog21assignment.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Component
public class SeedData {
    private final QueenEntityRepository<Album> albumRepository;
    private final QueenEntityRepository<Song> songRepository;
    private static final Logger log = LoggerFactory.getLogger(SeedData.class);

    @Autowired
    public SeedData(QueenEntityRepository<Album> albumRepository, QueenEntityRepository<Song> songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    public void seed(String... args) {
        log.debug("Seeding database");

        Album queen = albumRepository.create(new Album("Queen",  LocalDate.of(1973, 7, 13)));

        List<Song> queenSongs = List.of(
                new Song("Keep Yourself Alive", 3.47, List.of(Genre.HARD_ROCK, Genre.POWER_POP),
                        YearMonth.of(1972, 11), queen),
                new Song("Doing All Right", 4.09, List.of(Genre.HARD_ROCK, Genre.PROGRESSIVE_ROCK),
                        YearMonth.of(1971, 8), queen),
                new Song("Great King Rat", 5.41, List.of(Genre.HARD_ROCK, Genre.HEAVY_METAL),
                        YearMonth.of(1971, 9), queen),
                new Song("My Fairy King", 4.07, List.of(Genre.PROGRESSIVE_ROCK, Genre.OPERA),
                        YearMonth.of(1971, 10), queen)
        );

        queenSongs.forEach(s -> {
            songRepository.create(s);
            queen.addSong(s);
        });


        Album queenII = albumRepository.create(new Album("Queen II",  LocalDate.of(1974, 3, 8)));

        List<Song> queenIISongs = List.of(
                new Song("Procession", 1.12, List.of(Genre.PROGRESSIVE_ROCK),
                        YearMonth.of(1973, 8), queenII),
                new Song("Father to Son", 6.14, List.of(Genre.HARD_ROCK, Genre.HEAVY_METAL),
                        YearMonth.of(1973, 8), queenII),
                new Song("White Queen (As It Began)", 4.34, List.of(Genre.GLAM_ROCK, Genre.HEAVY_METAL),
                        YearMonth.of(1974, 2), queenII),
                new Song("Some Day One Day", 4.23, List.of(Genre.GLAM_ROCK, Genre.HEAVY_METAL),
                        YearMonth.of(1974, 2), queenII),
                new Song("The Loser in the End", 4.02, List.of(Genre.HARD_ROCK, Genre.GLAM_ROCK),
                        YearMonth.of(1974, 2), queenII)
        );

        queenIISongs.forEach(s -> {
            songRepository.create(s);
            queenII.addSong(s);
        });

        Album sheerHeartAttack = albumRepository.create(new Album("Sheer Heart Attack",  LocalDate.of(1974, 11, 8)));

        List<Song> sheerHeartAttackSongs = List.of(
                new Song("Brighton Rock", 5.11, List.of(Genre.HARD_ROCK),
                        YearMonth.of(1974, 10), sheerHeartAttack),
                new Song("Killer Queen", 3.00, List.of(Genre.GLAM_ROCK, Genre.ART_ROCK),
                        YearMonth.of(1974, 8), sheerHeartAttack),
                new Song("Flick of the Wrist", 3.18, List.of(Genre.HARD_ROCK, Genre.HEAVY_METAL, Genre.GLAM_ROCK),
                        YearMonth.of(1974, 8), sheerHeartAttack)
        );

        sheerHeartAttackSongs.forEach(s -> {
            songRepository.create(s);
            sheerHeartAttack.addSong(s);
        });

        Album jazz = albumRepository.create(new Album("Jazz", LocalDate.of(1978, 11, 10)));

        List<Song> jazzSongs = List.of(
                new Song("Mustapha", 5.11, List.of(Genre.HARD_ROCK, Genre.PROGRESSIVE_ROCK, Genre.ART_ROCK),
                        YearMonth.of(1978, 10), jazz),
                new Song("Fat Bottomed Girls", 3.00, List.of(Genre.GLAM_ROCK, Genre.HARD_ROCK),
                        YearMonth.of(1978, 9), jazz),
                new Song("Jealousy", 3.18, List.of(Genre.HARD_ROCK, Genre.SOFT_ROCK),
                        YearMonth.of(1978, 10), jazz)
        );

        jazzSongs.forEach(s -> {
            songRepository.create(s);
            jazz.addSong(s);
        });

        log.debug("Finished seeding database");
    }
}
