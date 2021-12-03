package prog21assignment.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Song;
import prog21assignment.repository.QueenEntityRepository;

import java.util.List;

@Profile("jdbc")
@Repository
public class SongRepository implements QueenEntityRepository<Song> {
    @Override
    public Song create(Song song) {
        return null;
    }

    @Override
    public List<Song> read() {
        return null;
    }

    @Override
    public void update(Song song) {

    }

    @Override
    public void delete(Song song) {

    }

    @Override
    public Song findById(int id) {
        return null;
    }
}
