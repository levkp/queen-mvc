package queenapp.service;

import queenapp.domain.Album;
import queenapp.presentation.dto.AlbumDto;
import queenapp.service.QueenEntityService;

import java.util.List;

public interface AlbumService extends QueenEntityService<Album> {
    AlbumDto createFromDto(AlbumDto dto, String ownerUsername);
    AlbumDto updateFromDto(AlbumDto dto, String ownerUsername);


}
