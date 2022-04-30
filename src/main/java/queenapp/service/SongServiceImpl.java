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

import static queenapp.security.AdminSessionVerifier.isAdminSession;

@Service
public class SongServiceImpl implements SongService {
    private final QueenUserService userService;
    private final QueenEntityService<Album> albumService;
    private final QueenEntityRepository<Song> repository;
    private final QueenEntityDtoMapper<SongDto, Song> mapper;

    @Autowired
    public SongServiceImpl(QueenUserService userService,
                           @Lazy QueenEntityService<Album> albumService,
                           QueenEntityRepository<Song> repository,
                           QueenEntityDtoMapper<SongDto, Song> mapper) {
        this.repository = repository;
        this.albumService = albumService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public List<Song> findAll() {
        return repository.findAll();
    }

    @Override
    public SongDto createFromDto(SongDto dto, String ownerUsername) {
        QueenUser owner = userService.findByUsername(ownerUsername);

        return null;
    }

    @Override
    public SongDto updateFromDto(SongDto dto, String ownerUsername) {


        return null;
    }

    private void upsertFromDto(Song s, SongDto dto, QueenUser user) {
        boolean isAdminSession = isAdminSession();
        mapper.fromDto(dto, s);

        Album a = albumService.findById(dto.getAlbumId());

        if (!isAdminSession && a.getOwner() != null && !a.getOwner().equals(s.getOwner())) {
            throw new OwnershipException(Album.class, a.getId());
        }

        // Todo
        a.addSong(s);
        s.setAlbum(a);

        repository.update(s);
    }


    @Override
    public void updateOwner(Song s, String ownerUsername) {
        updateOwner(s, userService.findByUsername(ownerUsername));
    }





    // Todo
    @Override
    public void updateOwner(Song s, QueenUser owner) {
        if (isAdminSession()) {
            s.setOwner(owner);
            repository.update(s);
        }
        /*
        else {
            if (s.getOwner() == null) {
                if (s.getAlbum() == null || s.getAlbum().getOwner().equals(owner)) {
                    s.setOwner(owner);
                    repository.update(s);
                } else {
                    throw new MissingOwnershipException(Song.class, s.getId());
                }
            } else {
                if (!s.getOwner().equals(owner)) {
                    throw new MissingOwnershipException(Song.class, s.getId());
                }
            }
        }
        */
    }

    @Override
    public void delete(Song s) {
        repository.delete(s);
    }

    // Todo
    public Song upsert(Song s, int albumId, String ownerUsername) {
        QueenUser owner = userService.findByUsername(ownerUsername);

        if (s.getOwner() != null) {
            updateOwner(s, owner);
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
