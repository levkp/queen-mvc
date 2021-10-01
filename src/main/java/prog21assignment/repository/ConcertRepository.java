package prog21assignment.repository;

import prog21assignment.domain.Concert;
import java.util.List;

public interface ConcertRepository {
    Concert createConcert(Concert c);
    List<Concert> readConcerts();
}
