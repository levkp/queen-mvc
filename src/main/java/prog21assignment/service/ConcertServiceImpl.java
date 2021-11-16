package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Concert;
import prog21assignment.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConcertServiceImpl implements ConcertService {

    private final QueenEntityRepository<Concert> repository;

    @Autowired
    public ConcertServiceImpl(QueenEntityRepository<Concert>  repository) {
        this.repository = repository;
    }

    public Concert create(int attendance, String name, String location, LocalDate date) {
        return repository.create(new Concert(attendance, name, location, date));
    }

    @Override
    public List<Concert> read() {
        return repository.read();
    }
}
