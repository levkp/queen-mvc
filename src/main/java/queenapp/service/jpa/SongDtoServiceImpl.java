package queenapp.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import queenapp.domain.Song;
import queenapp.exception.EntityNotFoundException;
import queenapp.presentation.dto.SongDto;
import queenapp.repository.QueenEntityRepository;
import queenapp.service.QueenEntityDtoService;

import java.util.List;
import java.util.Optional;

@Profile("jpa")
@Service
public class SongDtoServiceImpl implements QueenEntityDtoService<SongDto> {
    private final QueenEntityRepository<Song> repository;

    @Autowired
    public SongDtoServiceImpl(QueenEntityRepository<Song> repository) {
        this.repository = repository;
    }

    @Override
    public SongDto create(SongDto songDto) {
        return null;
    }

    @Override
    public List<SongDto> read() {
        return null;
    }

    @Override
    public SongDto updateById(int id, SongDto songDto) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public SongDto findById(int id) {
        Optional<Song> o = repository.findById(id);
        if (o.isEmpty()) throw new EntityNotFoundException(Song.class, id);
        return SongDto.fromSong(o.get());
    }
}
