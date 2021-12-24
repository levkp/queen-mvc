package prog21assignment.repository;

import prog21assignment.domain.QueenEntity;

import java.util.List;

public interface QueenEntityRepository<T extends QueenEntity> {
    T create(T t);
    List<T> read();
    T findById(int id);
}
