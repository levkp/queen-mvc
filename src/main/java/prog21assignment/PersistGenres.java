package prog21assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import prog21assignment.repository.jpa.GenreRepository;

@Profile("jpa")
@Component
public class PersistGenres implements CommandLineRunner {
    private final GenreRepository repository;

    @Autowired
    private PersistGenres(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.persistAll();
    }
}
