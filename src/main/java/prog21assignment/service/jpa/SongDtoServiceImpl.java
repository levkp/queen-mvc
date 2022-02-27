package prog21assignment.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Song;
import prog21assignment.exceptions.EntityNotFoundException;
import prog21assignment.presentation.dto.SongDto;
import prog21assignment.repository.QueenEntityRepository;
import prog21assignment.service.QueenEntityDtoService;

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
