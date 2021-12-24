//package prog21assignment;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import prog21assignment.domain.Album;
//import prog21assignment.repository.QueenEntityRepository;
//
//@Component
//public class TestRunner implements CommandLineRunner {
//
//    private static final Logger log = LoggerFactory.getLogger(TestRunner.class);
//
//    @Autowired
//    private QueenEntityRepository<Album> repository;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        log.debug("Current repository: " + repository);
//
//        /*
//        System.out.println("HELLO FROM CMD RUNNER ");
//        System.out.println("-------------------------");
//        Album a = new Album("Hibernate album", LocalDate.now());
//
//        Song s = new Song("Hibernate song", 3.4, List.of(Genre.ART_ROCK, Genre.GLAM_ROCK), YearMonth.now(), a);
//        a.addSong(s);
//
//        repository.create(a);
//
//        System.out.println(repository.toString());
//        */
//    }
//}
