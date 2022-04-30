package queenapp.repository;

import queenapp.domain.QueenEntity;

import java.util.List;
import java.util.Optional;

public interface QueenEntityRepository<T extends QueenEntity> {
    T create(T t);
    List<T> findAll();
    Optional<T> findById(int id);
    T update(T t);
    void delete(T t);
    void deleteAll();
}
