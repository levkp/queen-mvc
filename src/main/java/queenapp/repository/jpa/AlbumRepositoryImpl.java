package queenapp.repository.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import queenapp.domain.Album;
import queenapp.repository.QueenEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Profile("jpa")
@Repository
@Transactional
public class AlbumRepositoryImpl implements QueenEntityRepository<Album> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Album create(Album a) {
        log.debug("Persisting album with title " + a.getTitle());
        manager.persist(a);
        return a;
    }

    @Override
    public List<Album> read() {
        return manager.createQuery("select a from Album a").getResultList();
    }

    @Override
    public Album update(Album a) {
        manager.persist(manager.contains(a) ? a : manager.merge(a));
        return a;
    }

    @Override
    public void delete(Album a) {
        manager.remove(manager.contains(a) ? a : manager.merge(a));
    }

    @Override
    public Optional<Album> findById(int id) {
        return Optional.ofNullable(manager.find(Album.class, id));
    }
}
