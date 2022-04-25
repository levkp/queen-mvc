package queenapp.presentation.dto;

import queenapp.domain.QueenEntity;

public interface QueenEntityDtoMapper<X extends QueenEntityDto, Y extends QueenEntity> {
    Y fromDto(X dto);
    X toDto(Y o);
}
