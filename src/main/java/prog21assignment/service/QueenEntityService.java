package prog21assignment.service;

import prog21assignment.domain.QueenEntity;

import java.util.List;

public interface QueenEntityService<T extends QueenEntity> {
    T create(T t);
    List<T> read();
    T findById(int id);
}
