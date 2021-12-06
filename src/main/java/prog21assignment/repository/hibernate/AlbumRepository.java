package prog21assignment.repository.hibernate;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Album;
import prog21assignment.repository.QueenEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Profile("hibernate")
@Repository
@Transactional
public class AlbumRepository implements QueenEntityRepository<Album> {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Album create(Album a) {
        manager.persist(a);
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
        return manager.find(Album.class, id);
    }
}
