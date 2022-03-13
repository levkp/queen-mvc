package queenapp.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import queenapp.domain.Song;
import queenapp.repository.QueenEntityRepository;

import java.util.List;

@Service("jdbc")
@Repository
public class SongRepository implements QueenEntityRepository<Song> {
    private JdbcTemplate template;
    private SimpleJdbcInsert inserter;

    public SongRepository(JdbcTemplate template) {
        this.template = template;
        this.inserter = new SimpleJdbcInsert(template).withTableName("song").usingGeneratedKeyColumns("id");
    }

    @Override
    public Song create(Song song) {
        return null;
    }

    @Override
    public List<Song> read() {
        return null;
    }

    @Override
    public Song findById(int id) {
        return null;
    }
}
