package queenapp.service;

import queenapp.presentation.dto.QueenEntityDto;

import java.util.List;

public interface QueenEntityDtoService<T extends QueenEntityDto> {
    T create(T t, String ownerUsername);
    List<T> read();
    T updateById(int id, T t);
    void deleteById(int id);
    T findById(int id);
}
