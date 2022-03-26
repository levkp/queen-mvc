package queenapp.service.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import queenapp.domain.Song;
import queenapp.service.QueenEntityService;

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
