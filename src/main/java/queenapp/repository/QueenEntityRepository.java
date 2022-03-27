package queenapp.repository;

import queenapp.domain.QueenEntity;

import java.util.List;
import java.util.Optional;

public interface QueenEntityRepository<T extends QueenEntity> {
    T create(T t);
    List<T> read();
    Optional<T> findById(int id);

    default T update(T t) {
        return t;
    }

    default void delete(T t) {

    }

    default Optional<T> findByTitle(String title) {
        return null;
    }
}
