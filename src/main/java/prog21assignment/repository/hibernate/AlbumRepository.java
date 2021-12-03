package prog21assignment.repository.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Album;
import prog21assignment.repository.QueenEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Profile("hibernate")
@Repository
public class AlbumRepository implements QueenEntityRepository<Album> {
    @PersistenceUnit
    private final EntityManagerFactory factory;

    @Autowired
    public AlbumRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Album create(Album a) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(a);
        manager.getTransaction().commit();
        manager.close();
        return a;
    }

    @Override
    public List<Album> read() {
        return null;
    }

    @Override
    public void update(Album a) {

    }

    @Override
    public void delete(Album a) {

    }

    @Override
    public Album findById(int id) {
        Album a;
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        a = manager.find(Album.class, id);
        manager.getTransaction().commit();
        manager.close();
        return a;
    }
}
