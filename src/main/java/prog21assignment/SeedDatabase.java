package prog21assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Profile("jpa")
@DependsOn("persistGenres")
@Component
public class SeedDatabase implements CommandLineRunner {
    private final QueenEntityRepository<Album> albumRepository;
    private final QueenEntityRepository<Song> songRepository;

    @Autowired
    public SeedDatabase(QueenEntityRepository<Album> albumRepository, QueenEntityRepository<Song> songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Album queen = albumRepository.create(new Album("Queen",  LocalDate.of(1973, 7, 13)));
//        albumRepository.create(new Album("Queen II",  LocalDate.of(1974, 3, 8)));
//        albumRepository.create(new Album("Sheer Heart Attack",  LocalDate.of(1974, 11, 8)));
//        albumRepository.create(new Album("Jazz", LocalDate.of(1978, 11, 10)));

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

    }
}
