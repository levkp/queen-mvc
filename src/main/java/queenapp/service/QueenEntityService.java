package queenapp.service;

import queenapp.domain.QueenEntity;
import queenapp.domain.QueenUser;

import java.util.List;

public interface QueenEntityService<T extends QueenEntity> {
    List<T> findAll();
    T findById(int id);
    void deleteAll();
    void delete(T t);

    void updateOwner(T t, String ownerUsername);

    default void updateOwner(T t, QueenUser owner) {

    }


}
