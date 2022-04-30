package queenapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import queenapp.domain.Album;
import queenapp.domain.QueenUser;
import queenapp.domain.Song;
import queenapp.exception.EntityNotFoundException;
import queenapp.exception.OwnershipException;
import queenapp.exception.SongAlreadyInAlbumException;
import queenapp.presentation.dto.AlbumDto;
import queenapp.presentation.dto.QueenEntityDtoMapper;
import queenapp.repository.QueenEntityRepository;

import java.util.ArrayList;
import java.util.List;

import static queenapp.security.AdminSessionVerifier.isAdminSession;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final QueenUserService userService;
    private final QueenEntityService<Song> songService;
    private final QueenEntityRepository<Album> albumRepository;
    private final QueenEntityDtoMapper<AlbumDto, Album> albumMapper;

    @Autowired
    public AlbumServiceImpl(QueenUserService userService,
                            QueenEntityService<Song> songService,
                            QueenEntityRepository<Album> albumRepository,
                            QueenEntityDtoMapper<AlbumDto, Album> albumMapper) {
        this.userService = userService;
        this.songService = songService;
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    @Override
    public AlbumDto createFromDto(AlbumDto dto, String ownerUsername) {
        QueenUser owner = userService.findByUsername(ownerUsername);
        upsertFromDto(new Album(), dto, owner);
        return dto;
    }

    @Override
    public AlbumDto updateFromDto(AlbumDto dto, String ownerUsername) {
        boolean isAdminSession = isAdminSession();
        QueenUser owner = userService.findByUsername(ownerUsername);
        Album a = findById(dto.getId());

        if (!isAdminSession && !a.getOwner().equals(owner)) {
            throw new OwnershipException(Album.class, dto.getId());
        }

        upsertFromDto(a, dto, owner);
        return dto;
    }

    private void upsertFromDto(Album a, AlbumDto dto, QueenUser user) {
        boolean isAdminSession = isAdminSession();
        List<Song> playlist  = new ArrayList<>();
        albumMapper.fromDto(dto, a);

        dto.getSongIds().forEach(id -> {
            Song s = songService.findById(id);

            if (s.getAlbum() != null) {
                throw new SongAlreadyInAlbumException(s.getId());
            }
            if (!isAdminSession && s.getOwner() != null && !s.getOwner().equals(user)) {
                throw new OwnershipException(Song.class, id);
            }

            playlist.add(s);
            s.setAlbum(a);
        });

        a.getSongs().addAll(playlist);
        albumRepository.create(a);
        // Todo: do I have to update each song manually too?
        dto.setId(a.getId());
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album findById(int id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Album.class, id));
    }

    @Override
    public void deleteAll() {
        albumRepository.deleteAll();
    }

    @Override
    public void delete(Album a) {
        albumRepository.delete(a);
    }



    @Override
    public void updateOwner(Album a, String username) {
        updateOwner(a, userService.findByUsername(username));
    }

    // Todo
    @Override
    public void updateOwner(Album a, QueenUser owner) {
        if (isAdminSession()) {
            a.setOwner(owner);
        }
        /*
        else {
            if (a.getOwner() == null) {
                a.setOwner(owner);

            } else {
                throw new MissingOwnershipException(Album.class, a.getId());
            }
        }
        */

        // Todo should I update each song too?
//        a.getSongs().forEach(s -> songService.updateOwner(s, owner));
        albumRepository.update(a);
    }

}
