package prog21assignment.repository;

import prog21assignment.domain.Album;
import java.util.List;

public interface AlbumRepository {
    Album createAlbum(Album a);
    List<Album> readAlbums();
}
