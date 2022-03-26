package queenapp.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import queenapp.domain.Album;
import queenapp.domain.Genre;
import queenapp.domain.QueenUser;
import queenapp.domain.Song;
import queenapp.repository.QueenEntityRepository;
import queenapp.repository.QueenUserRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;

@Component
public class QueenData {
    private final QueenUserRepository userRepository;
    private final QueenEntityRepository<Album> albumRepository;
    private final QueenEntityRepository<Song> songRepository;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public QueenData(QueenUserRepository userRepository, QueenEntityRepository<Album> albumRepository, QueenEntityRepository<Song> songRepository) {
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    public void seed() {
        log.debug("Seeding database");

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

//        $2a$10$PXi6GGA2YJCHVf7OSZW5suzT5J2nTi72ZhrT4lT1EU4HhEQgxopzu
//        $2a$10$LqLqgrrWBlA1M3tvwtFPn.yV9D/Bl2XW3cB.Sm047Gu8xkXDZbIRm

        QueenUser standard = new QueenUser("standard", bcrypt.encode("standard"),false);
        QueenUser admin = new QueenUser("admin", bcrypt.encode("admin"),true);

        userRepository.create(standard);
        userRepository.create(admin);

        String queenDesc = """
                The album was influenced by heavy metal and progressive rock. The lyrics are based on a variety of topics, including\s
                folklore and religion. Lead singer Freddie Mercury wrote five of the ten tracks, lead guitarist Brian May wrote four\s
                songs (including "Doing All Right", which he co-wrote with Tim Staffell while in the band Smile), and drummer Roger
                Taylor both wrote and sang "Modern Times Rock and Roll". The final song on the album is a short instrumental version of
                 "Seven Seas of Rhye", the full version of which would appear on the band's second album, Queen II.""";

        Album queen = albumRepository.create(new Album("Queen",  LocalDate.of(1973, 7, 13), queenDesc, standard));
        
        List<Song> queenSongs = List.of(
                new Song("Keep Yourself Alive", 3.47, Set.of(Genre.HARD_ROCK, Genre.POWER_POP),
                        YearMonth.of(1972, 11), queen),
                new Song("Doing All Right", 4.09, Set.of(Genre.HARD_ROCK, Genre.PROGRESSIVE_ROCK),
                        YearMonth.of(1971, 8), queen),
                new Song("Great King Rat", 5.41, Set.of(Genre.HARD_ROCK, Genre.HEAVY_METAL),
                        YearMonth.of(1971, 9), queen),
                new Song("My Fairy King", 4.07, Set.of(Genre.PROGRESSIVE_ROCK, Genre.OPERA),
                        YearMonth.of(1971, 10), queen)
        );

        queenSongs.forEach(s -> {
            songRepository.create(s);
            queen.addSong(s);
        });

        String queenIIDesc = """
                Described as "arguably the heaviest Queen album", Queen II marked the end of the first phase of the band's career.
                The album combines a heavy rock sound with art rock and progressive rock elements, and has been called "a pillar of grandiose, assaultive hard rock" by the Rock and Roll Hall of Fame.
                Queen II is not a concept album but a collection of songs with a loose theme running throughout.""";


        Album queenII = albumRepository.create(new Album("Queen II",  LocalDate.of(1974, 3, 8), queenIIDesc, standard));

        List<Song> queenIISongs = List.of(
                new Song("Procession", 1.12, Set.of(Genre.PROGRESSIVE_ROCK),
                        YearMonth.of(1973, 8), queenII),
                new Song("Father to Son", 6.14, Set.of(Genre.HARD_ROCK, Genre.HEAVY_METAL),
                        YearMonth.of(1973, 8), queenII),
                new Song("White Queen (As It Began)", 4.34, Set.of(Genre.GLAM_ROCK, Genre.HEAVY_METAL),
                        YearMonth.of(1974, 2), queenII),
                new Song("Some Day One Day", 4.23, Set.of(Genre.GLAM_ROCK, Genre.HEAVY_METAL),
                        YearMonth.of(1974, 2), queenII),
                new Song("The Loser in the End", 4.02, Set.of(Genre.HARD_ROCK, Genre.GLAM_ROCK),
                        YearMonth.of(1974, 2), queenII)
        );

        queenIISongs.forEach(s -> {
            songRepository.create(s);
            queenII.addSong(s);
        });

        Album sheerHeartAttack = albumRepository.create(new Album("Sheer Heart Attack",  LocalDate.of(1974, 11, 8), standard));

        List<Song> sheerHeartAttackSongs = List.of(
                new Song("Brighton Rock", 5.11, Set.of(Genre.HARD_ROCK),
                        YearMonth.of(1974, 10), sheerHeartAttack),
                new Song("Killer Queen", 3.00, Set.of(Genre.GLAM_ROCK, Genre.ART_ROCK),
                        YearMonth.of(1974, 8), sheerHeartAttack),
                new Song("Flick of the Wrist", 3.18, Set.of(Genre.HARD_ROCK, Genre.HEAVY_METAL, Genre.GLAM_ROCK),
                        YearMonth.of(1974, 8), sheerHeartAttack)
        );

        sheerHeartAttackSongs.forEach(s -> {
            songRepository.create(s);
            sheerHeartAttack.addSong(s);
        });

        Album jazz = albumRepository.create(new Album("Jazz", LocalDate.of(1978, 11, 10), admin));

        List<Song> jazzSongs = List.of(
                new Song("Mustapha", 5.11, Set.of(Genre.HARD_ROCK, Genre.PROGRESSIVE_ROCK, Genre.ART_ROCK),
                        YearMonth.of(1978, 10), jazz),
                new Song("Fat Bottomed Girls", 3.00, Set.of(Genre.GLAM_ROCK, Genre.HARD_ROCK),
                        YearMonth.of(1978, 9), jazz),
                new Song("Jealousy", 3.18, Set.of(Genre.HARD_ROCK, Genre.SOFT_ROCK),
                        YearMonth.of(1978, 10), jazz)
        );

        jazzSongs.forEach(s -> {
            songRepository.create(s);
            jazz.addSong(s);
        });

        log.debug("Finished seeding database");
    }
}
