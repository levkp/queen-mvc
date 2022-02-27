//package prog21assignment.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import prog21assignment.domain.Album;
//import prog21assignment.domain.Song;
//import prog21assignment.exceptions.EntityNotFoundException;
//import prog21assignment.exceptions.NoContentException;
//import prog21assignment.presentation.dto.AlbumDto;
//import prog21assignment.repository.QueenEntityRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public class NewServiceImpl implements NewService<AlbumDto> {
//    private final QueenEntityRepository<Album> albumRepository;
//    private final QueenEntityRepository<Song> songRepository;
//
//    @Autowired
//    public NewServiceImpl(QueenEntityRepository<Album> albumRepository, QueenEntityRepository<Song> songRepository) {
//        this.albumRepository = albumRepository;
//        this.songRepository = songRepository;
//    }
//
//    @Override
//    public AlbumDto create(AlbumDto dto) {
//        return null;
//    }
//
//    @Override
//    public List<AlbumDto> read() {
//        List<Album> albums = albumRepository.read();
//        if (albums.isEmpty()) throw new NoContentException();
//        return albums.stream()
//                .map(AlbumDto::fromAlbum)
//                .toList();
//    }
//
//    @Override
//    public AlbumDto updateById(int id, AlbumDto dto) {
//        Optional<Album> o = albumRepository.findById(id);
//
//        if (o.isEmpty()) {
//            throw new EntityNotFoundException("Unable to find album with id " + id);
//        }
//
//        Album a = o.get();
//        a.setTitle(dto.getTitle());
//        // Todo: moving parsing logic to service
//        a.setRelease(dto.getParsedRelease());
//        a.setDescription(dto.getDescription());
//
//        dto.getSongIds().forEach(songId -> {
//            Song s = songRepository.fin
//        });
//
//        dto.setId(id);
//        return dto;
//    }
//
//    @Override
//    public void delete(int id) {
//        // Todo
//        albumRepository.delete(null);
//    }
//
//    @Override
//    public AlbumDto findById(int id) {
//        Optional<Album> o = albumRepository.findById(id);
//        if (o.isEmpty()) throw new EntityNotFoundException("Unable to find album with id " + id);
//        return AlbumDto.fromAlbum(o.get());
//    }
//}
