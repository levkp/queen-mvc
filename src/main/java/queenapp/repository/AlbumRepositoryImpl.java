package queenapp.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import queenapp.domain.Album;

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

    @Override
    public Optional<Album> findByTitle(String title) {
        return Optional.ofNullable((Album) manager.createQuery("select a from Album a where a.title =:title")
                .setParameter("title", title)
                .getResultList()
                .get(0));
    }

    @Override
    public void deleteAll() {
        for(Album a : read()) {
            delete(a);
        }

        // Todo
//        manager.createNativeQuery("truncate table song_genre and commit;").executeUpdate();
//        manager.createQuery("delete from Song s where s.album is not null").executeUpdate();
//        manager.createQuery("delete from Album").executeUpdate();
//        System.out.println("Done");
    }
}
