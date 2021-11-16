package prog21assignment.repository;

import prog21assignment.domain.QueenEntity;

import java.util.List;

public interface QueenEntityRepository<T extends QueenEntity> {
    T create(T t);
    List<T> read();
    void update(T t);
    void delete(T t);
    T findById(int id);
}
