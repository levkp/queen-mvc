package queenapp.service;

import queenapp.domain.QueenEntity;
import queenapp.domain.QueenUser;

import java.util.List;

public interface QueenEntityService<T extends QueenEntity> {
    T create(T t, String ownerUsername);
    List<T> read();
    T findById(int id);
    void deleteAll();
    default void updateOwner(T a, String ownerUsername) {

    }

    default void delete(T t) {

    }


}
