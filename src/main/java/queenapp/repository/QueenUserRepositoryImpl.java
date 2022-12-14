package queenapp.repository;

import org.springframework.stereotype.Repository;
import queenapp.domain.QueenUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class QueenUserRepositoryImpl implements QueenUserRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public QueenUser create(QueenUser user) {
        manager.persist(user);
        return user;
    }

    @Override
    public Optional<QueenUser> findByUsername(String username) {
        return Optional.ofNullable((QueenUser)manager
                .createQuery("select u from QueenUser u where u.username like :username")
                .setParameter("username", username)
                .getResultList()
                .get(0));
    }
}
