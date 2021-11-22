package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Concert;
import prog21assignment.repository.QueenEntityRepository;

import java.util.List;

@Component
public class ConcertServiceImpl implements QueenEntityService<Concert> {

    private final QueenEntityRepository<Concert> repository;

    @Autowired
    public ConcertServiceImpl(QueenEntityRepository<Concert>  repository) {
        this.repository = repository;
    }

    @Override
    public Concert create(Concert c) {
        return repository.create(c);
    }

    @Override
    public List<Concert> read() {
        return repository.read();
    }

    @Override
    public void update(Concert concert) {

    }

    @Override
    public void delete(Concert concert) {

    }

    @Override
    public Concert findById(int id) {
        return null;
    }
}
