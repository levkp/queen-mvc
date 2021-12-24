package prog21assignment.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Concert;
import prog21assignment.repository.QueenEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Profile("jpa")
@Repository
@Transactional
public class ConcertRepository implements QueenEntityRepository<Concert> {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Concert create(Concert c) {
        manager.persist(c);
        return c;
    }

    @Override
    public List<Concert> read() {
        return manager.createQuery("select c from Concert c").getResultList();
    }

    @Override
    public Concert findById(int id) {
        return manager.find(Concert.class, id);
    }
}
