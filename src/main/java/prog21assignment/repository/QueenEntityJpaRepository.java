package prog21assignment.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import prog21assignment.domain.QueenEntity;

@Profile("prod")
@Component
public interface QueenEntityJpaRepository<T extends QueenEntity> extends JpaRepository<T, Integer> {

}
