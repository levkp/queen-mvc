package prog21assignment.repository.hibernate;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Concert;
import prog21assignment.repository.QueenEntityRepository;

import java.util.List;

@Profile("hibernate")
@Repository
public class ConcertRepository implements QueenEntityRepository<Concert> {

    @Override
    public Concert create(Concert concert) {
        return null;
    }

    @Override
    public List<Concert> read() {
        return null;
    }

    @Override
    public void update(Concert concert) {

    }

    @Override
    public void delete(Concert concert) {

    }

    @Override
    public Concert findById(int id) {
        return null;
    }
}
