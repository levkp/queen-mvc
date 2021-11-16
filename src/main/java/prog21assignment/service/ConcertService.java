package prog21assignment.service;

import prog21assignment.domain.Concert;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public interface ConcertService {
    Concert create(int attendance, String name, String location, LocalDate date);
    List<Concert> read();
}
