package queenapp.service;

import queenapp.domain.Album;
import queenapp.presentation.dto.AlbumDto;
import queenapp.service.QueenEntityService;

import java.util.List;

public interface AlbumService extends QueenEntityService<Album> {
    void createFromDto(AlbumDto dto, String ownerUsername);
    void updateFromDto(AlbumDto dto, String ownerUsername);


}
