package queenapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import queenapp.domain.Album;
import queenapp.domain.QueenUser;
import queenapp.domain.Song;
import queenapp.exception.EntityNotFoundException;
import queenapp.presentation.dto.AlbumDto;
import queenapp.repository.QueenEntityRepository;
import queenapp.repository.QueenUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumDtoServiceImpl implements QueenEntityDtoService<AlbumDto> {
    private final QueenEntityRepository<Album> albumRepository;
    private final QueenEntityRepository<Song> songRepository;
    private final QueenUserRepository userRepository;

    @Autowired
    public AlbumDtoServiceImpl(QueenEntityRepository<Album> albumRepository, QueenEntityRepository<Song> songRepository,
                               QueenUserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AlbumDto create(AlbumDto dto, java.lang.String ownerUsername) {
        QueenUser owner = userRepository.findByUsername(ownerUsername).get();
        Album a = new Album();
        a.setOwner(owner);
        upsertFromDto(a, dto);
        return dto;
    }

    @Override
    public List<AlbumDto> read() {
        return albumRepository.read()
                .stream()
                .map(AlbumDto::fromAlbum)
                .collect(Collectors.toList());
    }

    @Override
    public AlbumDto updateById(int id, AlbumDto dto) {
        Optional<Album> o = albumRepository.findById(id);
        if (o.isEmpty()) throw new EntityNotFoundException(Album.class, id);
        upsertFromDto(o.get(), dto);
        return dto;
    }

    private void upsertFromDto(Album a, AlbumDto dto) {
        a.setTitle(dto.getTitle());
        a.setRelease(dto.getParsedRelease());
        a.setDescription(dto.getDescription());

        List<Song> songs = new ArrayList<>();
        dto.getSongIds().forEach(id -> {
            Optional<Song> o = songRepository.findById(id);
            if (o.isEmpty()) throw new EntityNotFoundException(Song.class, id);
            Song s = o.get();
            a.addSong(s);
            s.setAlbum(a);
            songs.add(s);
        });

        albumRepository.create(a);
        songs.forEach(songRepository::update);
        dto.setId(a.getId());
    }

    @Override
    public void deleteById(int id) {
        Optional<Album> o = albumRepository.findById(id);
        if (o.isEmpty()) throw new EntityNotFoundException(Album.class, id);
        albumRepository.delete(o.get());
    }

    @Override
    public AlbumDto findById(int id) {
        Optional<Album> o = albumRepository.findById(id);
        if (o.isEmpty()) throw new EntityNotFoundException(Album.class, id);
        return AlbumDto.fromAlbum(o.get());
    }

    @Override
    public AlbumDto findByTitle(String title) {
        Optional<Album> o = albumRepository.findByTitle(title);
        if (o.isEmpty()) throw new EntityNotFoundException();
        return AlbumDto.fromAlbum(o.get());
    }
}
