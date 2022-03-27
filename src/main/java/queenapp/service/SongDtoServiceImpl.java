package queenapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import queenapp.domain.QueenUser;
import queenapp.domain.Song;
import queenapp.exception.EntityNotFoundException;
import queenapp.presentation.dto.SongDto;
import queenapp.repository.QueenEntityRepository;
import queenapp.repository.QueenUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongDtoServiceImpl implements QueenEntityDtoService<SongDto> {
    private final QueenEntityRepository<Song> songRepository;
    private final QueenUserRepository userRepository;

    @Autowired
    public SongDtoServiceImpl(QueenEntityRepository<Song> repository, QueenUserRepository userRepository) {
        this.songRepository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public SongDto create(SongDto songDto, String ownerUsername) {
        QueenUser owner = userRepository.findByUsername(ownerUsername).get();

        // Todo
        return null;
    }

    @Override
    public List<SongDto> read() {
        return songRepository.read()
                .stream()
                .map(SongDto::fromSong)
                .collect(Collectors.toList());
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
        Optional<Song> o = songRepository.findById(id);
        if (o.isEmpty()) throw new EntityNotFoundException(Song.class, id);
        return SongDto.fromSong(o.get());
    }
}
