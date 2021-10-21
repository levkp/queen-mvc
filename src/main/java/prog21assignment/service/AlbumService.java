package prog21assignment.service;

import prog21assignment.domain.Album;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public interface AlbumService {
    Album addAlbum(String title, LocalDate release);
    List<Album> getAllAlbums();
    Album findById(int id);
}
