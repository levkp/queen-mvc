package prog21assignment.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prog21assignment.domain.Concert;
import prog21assignment.exceptions.DatabaseException;
import prog21assignment.repository.QueenEntityRepository;
import prog21assignment.service.QueenEntityService;

import java.util.List;

@Profile("jpa")
@Service
public class ConcertServiceImpl implements QueenEntityService<Concert> {
    private final QueenEntityRepository<Concert> repository;

    @Autowired
    public ConcertServiceImpl(QueenEntityRepository<Concert> repository) {
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
    public Concert findById(int id) {
        Concert c = repository.findById(id);

        if (c == null) {
            throw new DatabaseException(id, "Entity not found");
        }

        return c;
    }
}
