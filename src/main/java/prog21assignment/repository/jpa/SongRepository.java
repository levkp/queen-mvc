package prog21assignment.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Profile({"dev", "prod"})
@Repository
public class SongRepository implements QueenEntityRepository<Song> {
    @PersistenceContext
    private final EntityManager manager;

    @Autowired
    public SongRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Song create(Song s) {
        manager.getTransaction().begin();
        manager.persist(s);
        manager.getTransaction().commit();
        manager.close();
        return s;
    }

    @Override
    public List<Song> read() {
        return null;
    }

    @Override
    public void update(Song s) {

    }

    @Override
    public void delete(Song s) {

    }

    @Override
    public Song findById(int id) {
        manager.getTransaction().begin();
        Song s = manager.find(Song.class, id);
        manager.close();
        return s;
    }
}
