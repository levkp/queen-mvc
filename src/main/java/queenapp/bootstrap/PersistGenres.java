package queenapp.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import queenapp.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class PersistGenres implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void run(String... args) {
        log.debug("Persisting all genres");

        for (Genre g : Genre.values()) {
            manager.persist(g);
        }
    }
}
