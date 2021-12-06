package prog21assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prog21assignment.domain.QueenEntity;

public interface QueenEntityJpaRepository extends JpaRepository<QueenEntity, Integer> {

}
