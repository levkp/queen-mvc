package prog21assignment.repository.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Profile("jpa")
@Repository
@Transactional
public class GenreRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager manager;

    public void persistAll() {
        log.debug("Persisting all genres");

        for (Genre g : Genre.values()) {
            manager.persist(g);
        }
    }
}
