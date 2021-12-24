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

@Profile("jpa")
@Repository
@Transactional
public class SongRepository implements QueenEntityRepository<Song> {
    @PersistenceContext
    private final EntityManager manager;

    @Autowired
    public SongRepository(EntityManager manager) {
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
    public Song findById(int id) {
        return manager.find(Song.class, id);
    }
}
