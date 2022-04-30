package queenapp.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import queenapp.domain.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class SongRepositoryImpl implements QueenEntityRepository<Song> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @PersistenceContext
    private final EntityManager manager;

    @Autowired
    public SongRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Song create(Song s) {
        log.debug("Persisting song " + s.getTitle());
        manager.persist(s);
        return s;
    }

    @Override
    public List<Song> findAll() {
        return manager.createQuery("select s from Song s").getResultList();
    }

    @Override
    public Optional<Song> findById(int id) {
        return Optional.ofNullable(manager.find(Song.class, id));
    }

    @Override
    public Song update(Song s) {
        manager.persist(manager.contains(s) ? s : manager.merge(s));
        return s;
    }

    @Override
    public void delete(Song s) {
        log.debug("Deleting song " + s.getTitle());
        manager.remove(manager.contains(s) ? s : manager.merge(s));
    }

    @Override
    public void deleteAll() {
        // Todo: what is a better way to do this?
        for(Song s : findAll()) {
            delete(s);
        }
    }
}
