/*
package prog21assignment;

import prog21assignment.domain.Album;
import prog21assignment.domain.Concert;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class DataFactory {

    public static List<Album> albums = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>(); //One-to-many between Song and Album
    public static List<Concert> concerts = new ArrayList<>(); // many-to-many between Song and Concert

    static {
        seed();
    }

    public static void seed() {

        Concert shaManchester = new Concert(1904, "Sheer Heart Attack Manchester", "Palace Theatre, Manchester",
                LocalDate.of(1974, 10, 31));
        Concert shaHanley = new Concert(1364, "Sheer Heart Attack Shanley", "Victoria Hall, Shanley",
                LocalDate.of(1974, 10, 31));
        Concert shaLiverpool = new Concert(1841, "Sheer Heart Attack Liverpool", "Empire Theatre, Liverpool",
                LocalDate.of(1974, 11, 1));
        Concert shaLeeds = new Concert(1024, "Sheer Heart Attack Leeds", "University of Leeds Refectory",
                LocalDate.of(1974, 11, 2));

        concerts.addAll(List.of(shaManchester, shaHanley, shaLiverpool, shaLeeds));

        Album queen = new Album("Queen",  LocalDate.of(1973, 7, 13));
        Album queenII = new Album("Queen II",  LocalDate.of(1974, 3, 8));
        Album sheerHeartAttack = new Album("Sheer Heart Attack",  LocalDate.of(1974, 11, 8));

        albums.addAll(List.of(queen, queenII, sheerHeartAttack));

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

        songs.addAll(queenSongs);

        queenSongs.forEach(s -> {
            queen.addSong(s);
            shaManchester.addPlayedSong(s);
            shaHanley.addPlayedSong(s);
        });

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

        songs.addAll(queenIISongs);

        queenIISongs.forEach(s -> {
            queenII.addSong(s);
            shaLiverpool.addPlayedSong(s);
            shaLeeds.addPlayedSong(s);
        });

        List<Song> sheerHeartAttackSongs = List.of(
                new Song("Brighton Rock", 5.11, List.of(Genre.HARD_ROCK),
                        YearMonth.of(1974, 10), sheerHeartAttack),
                new Song("Killer Queen", 3.00, List.of(Genre.GLAM_ROCK, Genre.ART_ROCK),
                        YearMonth.of(1974, 8), sheerHeartAttack),
                new Song("Flick of the Wrist", 3.18, List.of(Genre.HARD_ROCK, Genre.HEAVY_METAL, Genre.GLAM_ROCK),
                        YearMonth.of(1974, 8), sheerHeartAttack)
        );

        songs.addAll(sheerHeartAttackSongs);

        sheerHeartAttackSongs.forEach(s -> {
            sheerHeartAttack.addSong(s);
            shaManchester.addPlayedSong(s);
            shaHanley.addPlayedSong(s);
            shaLiverpool.addPlayedSong(s);
            shaLeeds.addPlayedSong(s);
        });
    }
}
*/
