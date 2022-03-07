package prog21assignment.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Profile("jpa")
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
