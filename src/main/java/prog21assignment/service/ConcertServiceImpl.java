package prog21assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prog21assignment.domain.Concert;
import prog21assignment.repository.ConcertRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository repository;

    @Autowired
    public ConcertServiceImpl(ConcertRepository repository) {
        this.repository = repository;
    }

    @Override
    public Concert addConcert(int attendance, String name, String location, LocalDate date) {
        Concert c = new Concert(attendance, name, location, date);
        return repository.createConcert(c);
    }

    @Override
    public List<Concert> getAllConcerts() {
        return repository.readConcerts();
    }
}
