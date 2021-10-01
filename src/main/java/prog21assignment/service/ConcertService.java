package prog21assignment.service;

import prog21assignment.domain.Concert;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public interface ConcertService {
    Concert addConcert(int attendance, String name, String location, LocalDate date);
    List<Concert> getAllConcerts();
}
