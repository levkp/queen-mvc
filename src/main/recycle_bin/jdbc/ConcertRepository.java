package prog21assignment.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Concert;
import prog21assignment.repository.QueenEntityRepository;

import java.util.List;

@Profile("jdbc")
@Repository
public class ConcertRepository implements QueenEntityRepository<Concert> {
    private JdbcTemplate template;
    private SimpleJdbcInsert inserter;



    @Override
    public Concert create(Concert concert) {
        return null;
    }

    @Override
    public List<Concert> read() {
        return null;
    }

    @Override
    public Concert findById(int id) {
        return null;
    }
}
