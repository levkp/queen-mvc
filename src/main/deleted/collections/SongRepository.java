//package queenapp.repository.collections;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Repository;
//import queenapp.domain.Song;
//import queenapp.repository.QueenEntityRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Profile("dev2")
//@Repository
//public class SongRepository implements QueenEntityRepository<Song> {
//    private static final List<Song> songs = new ArrayList<>();
//    private static final Logger log = LoggerFactory.getLogger(SongRepository.class);
//
//
//    /*
//    static {
//        log.debug("Seeding song repository");
//        seed();
//    }
//
//    private static void seed() {
//
//        Album queen = AlbumRepository.albums.stream()
//                .filter(a -> a.getTitle().equals("Queen"))
//                .findAny()
//                .orElse(null);
//
//        if (queen != null) {
//            List<Song> queenSongs = List.of(
//                    new Song("Keep Yourself Alive", 3.47, List.of(Genre.HARD_ROCK, Genre.POWER_POP),
//                            YearMonth.of(1972, 11), queen),
//                    new Song("Doing All Right", 4.09, List.of(Genre.HARD_ROCK, Genre.PROGRESSIVE_ROCK),
//                            YearMonth.of(1971, 8), queen),
//                    new Song("Great King Rat", 5.41, List.of(Genre.HARD_ROCK, Genre.HEAVY_METAL),
//                            YearMonth.of(1971, 9), queen),
//                    new Song("My Fairy King", 4.07, List.of(Genre.PROGRESSIVE_ROCK, Genre.OPERA),
//                            YearMonth.of(1971, 10), queen)
//            );
//
//            queenSongs.forEach(s -> {
//                queen.addSong(s);
//                s.setId(songs.size());
//                songs.add(s);
//            });
//        }
//
//        Album queenII = AlbumRepository.albums.stream()
//                .filter(a -> a.getTitle().equals("Queen II"))
//                .findAny()
//                .orElse(null);
//
//        if (queenII != null) {
//            List<Song> queenIISongs = List.of(
//                    new Song("Procession", 1.12, List.of(Genre.PROGRESSIVE_ROCK),
//                            YearMonth.of(1973, 8), queenII),
//                    new Song("Father to Son", 6.14, List.of(Genre.HARD_ROCK, Genre.HEAVY_METAL),
//                            YearMonth.of(1973, 8), queenII),
//                    new Song("White Queen (As It Began)", 4.34, List.of(Genre.GLAM_ROCK, Genre.HEAVY_METAL),
//                            YearMonth.of(1974, 2), queenII),
//                    new Song("Some Day One Day", 4.23, List.of(Genre.GLAM_ROCK, Genre.HEAVY_METAL),
//                            YearMonth.of(1974, 2), queenII),
//                    new Song("The Loser in the End", 4.02, List.of(Genre.HARD_ROCK, Genre.GLAM_ROCK),
//                            YearMonth.of(1974, 2), queenII)
//            );
//
//            queenIISongs.forEach(s -> {
//                queenII.addSong(s);
//                s.setId(songs.size());
//                songs.add(s);
//            });
//        }
//
//        Album sheerHeartAttack = AlbumRepository.albums.stream()
//                .filter(a -> a.getTitle().equals("Sheer Heart Attack"))
//                .findAny()
//                .orElse(null);
//
//        if (sheerHeartAttack != null) {
//            List<Song> sheerHeartAttackSongs = List.of(
//                    new Song("Brighton Rock", 5.11, List.of(Genre.HARD_ROCK),
//                            YearMonth.of(1974, 10), sheerHeartAttack),
//                    new Song("Killer Queen", 3.00, List.of(Genre.GLAM_ROCK, Genre.ART_ROCK),
//                            YearMonth.of(1974, 8), sheerHeartAttack),
//                    new Song("Flick of the Wrist", 3.18, List.of(Genre.HARD_ROCK, Genre.HEAVY_METAL, Genre.GLAM_ROCK),
//                            YearMonth.of(1974, 8), sheerHeartAttack)
//            );
//
//            sheerHeartAttackSongs.forEach(s -> {
//                sheerHeartAttack.addSong(s);
//                s.setId(songs.size());
//                songs.add(s);
//            });
//        }
//
//
//        Album jazz = AlbumRepository.albums.stream()
//                .filter(a -> a.getTitle().equals("Jazz"))
//                .findAny()
//                .orElse(null);
//
//        if (jazz != null) {
//            List<Song> jazzSongs = List.of(
//                    new Song("Mustapha", 5.11, List.of(Genre.HARD_ROCK, Genre.PROGRESSIVE_ROCK, Genre.ART_ROCK),
//                            YearMonth.of(1978, 10), jazz),
//                    new Song("Fat Bottomed Girls", 3.00, List.of(Genre.GLAM_ROCK, Genre.HARD_ROCK),
//                            YearMonth.of(1978, 9), jazz),
//                    new Song("Jealousy", 3.18, List.of(Genre.HARD_ROCK, Genre.SOFT_ROCK),
//                            YearMonth.of(1978, 10), jazz)
//            );
//
//            jazzSongs.forEach(s -> {
//                jazz.addSong(s);
//                s.setId(songs.size());
//                songs.add(s);
//            });
//        }
//    }
//*/
//
//    @Override
//    public Song create(Song s) {
//        s.setId(songs.size());
//        songs.add(s);
//        s.getAlbum().addSong(s);
//        return s;
//    }
//
//    @Override
//    public List<Song> read() {
//        return songs;
//    }
//
//    @Override
//    public Optional<Song> findById(int id) {
//        return songs.stream()
//                .filter(s -> s.getId() == id)
//                .findFirst();
//    }
//}
