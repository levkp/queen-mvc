package prog21assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Album;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private QueenEntityRepository<Album> repository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("HELLO FROM CMD RUNNER ");
        System.out.println("-------------------------");
        Album a = new Album("Hibernate album", LocalDate.now());

        Song s = new Song("Hibernate song", 3.4, List.of(Genre.ART_ROCK, Genre.GLAM_ROCK), YearMonth.now(), a);
        a.addSong(s);

        repository.create(a);

        System.out.println(repository.toString());
    }
}
