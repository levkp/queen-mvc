package prog21assignment.service;

import prog21assignment.domain.Album;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public interface AlbumService {
    Album create(String title, LocalDate release);
    List<Album> read();
    Album findById(int id);
}
