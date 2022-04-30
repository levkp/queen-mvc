package queenapp.repository;

import queenapp.domain.Album;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public List<Album> findAll() {
        return manager.createQuery("select a from Album a").getResultList();
    }

    @Override
    public Optional<Album> findById(int id) {
        return Optional.ofNullable(manager.find(Album.class, id));
    }

    @Override
    public Album update(Album a) {
        manager.persist(manager.contains(a) ? a : manager.merge(a));
        return a;
    }

    @Override
    public void delete(Album a) {
        log.debug("Deleting album " + a.getTitle());
        manager.remove(manager.contains(a) ? a : manager.merge(a));
    }

    @Override
    public void deleteAll() {
        // Todo: what is a better way to do this?
        for(Album a : findAll()) {
            delete(a);
        }
    }
}
