package prog21assignment.repository.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Profile("hibernate")
@Repository
public class SongRepository implements QueenEntityRepository<Song> {
    @PersistenceUnit
    private final EntityManagerFactory factory;

    @Autowired
    public SongRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Song create(Song s) {
        EntityManager manager = factory.createEntityManager();
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
        Song s;
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        s = manager.find(Song.class, id);
        manager.close();
        return s;
    }
}
