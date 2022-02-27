package prog21assignment.service.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Song;
import prog21assignment.service.QueenEntityService;

import java.util.List;

@Profile("jdbc")
@Service
public class SongServiceImpl implements QueenEntityService<Song> {
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
