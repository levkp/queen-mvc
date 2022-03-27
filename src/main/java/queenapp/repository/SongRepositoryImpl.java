package queenapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import queenapp.domain.Song;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class SongRepositoryImpl implements QueenEntityRepository<Song> {
    @PersistenceContext
    private final EntityManager manager;

    @Autowired
    public SongRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Song create(Song s) {
        manager.persist(s);
        return s;
    }

    @Override
    public List<Song> read() {
        return manager.createQuery("select s from Song s").getResultList();
    }

    @Override
    public Song update(Song s) {
        manager.persist(manager.contains(s) ? s : manager.merge(s));
        return s;
    }

    @Override
    public Optional<Song> findById(int id) {
        return Optional.ofNullable(manager.find(Song.class, id));
    }
}
