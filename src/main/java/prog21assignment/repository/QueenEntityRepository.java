package prog21assignment.repository;

import prog21assignment.domain.QueenEntity;

import java.util.List;
import java.util.Optional;

public interface QueenEntityRepository<T extends QueenEntity> {
    T create(T t);
    List<T> read();
    Optional<T> findById(int id);

    default void delete(T t) {

    }
}
