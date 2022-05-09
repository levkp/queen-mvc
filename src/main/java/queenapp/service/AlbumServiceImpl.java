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
    public void createFromDto(AlbumDto dto, String ownerUsername) {
        QueenUser owner = userService.findByUsername(ownerUsername);
        Album a = new Album();
        a.setOwner(owner);
        upsertFromDto(a, dto, owner);
    }

    @Override
    public void updateFromDto(AlbumDto dto, String ownerUsername) {
        QueenUser owner = userService.findByUsername(ownerUsername);
        Album a = findById(dto.getId());
        if (!owner.isAdmin() && !a.getOwner().equals(owner)) {
            throw new OwnershipException(Album.class, dto.getId());
        }
        upsertFromDto(a, dto, owner);
    }

    private void upsertFromDto(Album a, AlbumDto dto, QueenUser user) {
        List<Song> playlist  = new ArrayList<>();
        albumMapper.fromDto(dto, a);

        dto.getSongIds().forEach(id -> {
            Song s = songService.findById(id);

            if (s.getAlbum() != null) {
                throw new SongAlreadyInAlbumException(s.getId());
            }
            if (!user.isAdmin() && s.getOwner() != null && !s.getOwner().equals(user)) {
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
//    @Transactional
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


}
