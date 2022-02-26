package prog21assignment.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Album;
import prog21assignment.domain.Song;
import prog21assignment.exceptions.EntityNotFoundException;
import prog21assignment.exceptions.NoContentException;
import prog21assignment.presentation.dto.AlbumDto;
import prog21assignment.repository.QueenEntityRepository;
import prog21assignment.service.QueenEntityDtoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("jpa")
@Service
public class AlbumDtoServiceImpl implements QueenEntityDtoService<AlbumDto> {
    private final QueenEntityRepository<Album> albumRepository;
    private final QueenEntityRepository<Song> songRepository;

    @Autowired
    public AlbumDtoServiceImpl(QueenEntityRepository<Album> albumRepository, QueenEntityRepository<Song> songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @Override
    public AlbumDto create(AlbumDto dto) {
         upsertFromDto(new Album(), dto);
         return dto;
    }

    @Override
    public List<AlbumDto> read() {
        List<Album> albums = albumRepository.read();
        if (albums.isEmpty()) throw new NoContentException();
        return albums.stream()
                .map(AlbumDto::fromAlbum)
                .toList();
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
            if (o.isEmpty()) throw new EntityNotFoundException(Song.    class, id);
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
}
