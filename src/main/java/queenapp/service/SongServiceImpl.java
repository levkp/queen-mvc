package queenapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import queenapp.domain.Album;
import queenapp.domain.QueenUser;
import queenapp.domain.Song;
import queenapp.exception.EntityNotFoundException;
import queenapp.exception.OwnershipException;
import queenapp.presentation.dto.QueenEntityDtoMapper;
import queenapp.presentation.dto.SongDto;
import queenapp.repository.QueenEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final QueenUserService userService;
    private final QueenEntityService<Album> albumService;
    private final QueenEntityRepository<Song> repository;
    private final QueenEntityDtoMapper<SongDto, Song> songMapper;

    @Autowired
    public SongServiceImpl(QueenUserService userService,
                           @Lazy QueenEntityService<Album> albumService,
                           QueenEntityRepository<Song> repository,
                           QueenEntityDtoMapper<SongDto, Song> mapper) {
        this.repository = repository;
        this.albumService = albumService;
        this.userService = userService;
        this.songMapper = mapper;
    }

    @Override
    public List<Song> findAll() {
        return repository.findAll();
    }

    @Override
    public void createFromDto(SongDto dto, String ownerUsername) {
        QueenUser owner = userService.findByUsername(ownerUsername);
        Song s = new Song();
        s.setOwner(owner);
        upsertFromDto(s, dto, owner);
    }

    @Override
    public void updateFromDto(SongDto dto, String ownerUsername) {


    }

    private void upsertFromDto(Song s, SongDto dto, QueenUser user) {
        songMapper.fromDto(dto, s);
        Album a = albumService.findById(dto.getAlbumId());

        if (!user.isAdmin() && a.getOwner() != null && !a.getOwner().equals(s.getOwner())) {
            throw new OwnershipException(Album.class, a.getId());
        }

        // Todo
        a.addSong(s);
        s.setAlbum(a);

        repository.update(s);
    }


    @Override
    public void delete(Song s) {
        repository.delete(s);
    }

    // Todo
    public Song upsert(Song s, int albumId, String ownerUsername) {
        QueenUser owner = userService.findByUsername(ownerUsername);

        if (s.getOwner() != null) {
            s.setOwner(owner);
//            updateOwner(s, owner);
        }

        Album a = albumService.findById(albumId);
        a.addSong(s);
        s.setAlbum(a);

        return repository.create(s);
    }

    @Override
    public Song findById(int id) {
        Optional<Song> o = repository.findById(id);
        if (o.isEmpty()) throw new EntityNotFoundException(Song.class, id);
        return o.get();
    }

    @Override
    public void deleteAll() {

    }



}
