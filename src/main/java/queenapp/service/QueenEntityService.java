package queenapp.service;

import queenapp.domain.QueenEntity;

import java.util.List;

public interface QueenEntityService<T extends QueenEntity> {
    T create(T t);
    List<T> read();
    T findById(int id);
    void deleteAll();
    default void updateOwner(T a, String username) {

    }


}
