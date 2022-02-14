package prog21assignment.service;

import prog21assignment.domain.QueenEntity;

import java.util.List;

public interface QueenEntityService<T extends QueenEntity> {
    T create(T t);
    List<T> read();

    default void update(T t) {

    }

    default void delete(int id) {

    }

    T findById(int id);
}
